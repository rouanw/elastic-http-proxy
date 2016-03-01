package example;

import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchClient;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchNode;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchSetting;
import com.github.tlrx.elasticsearch.test.support.junit.runners.ElasticsearchRunner;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(ElasticsearchRunner.class)
public class ExampleControllerIntegrationTest {

    @ElasticsearchNode(name = "node0", settings = {
        @ElasticsearchSetting(name = "http.enabled", value = "true")
    })
    Node node;

    @ElasticsearchClient(nodeName = "node0")
    Client client;

    private ExampleController exampleController;

    @Before
    public void setUp() throws Exception {
        exampleController = new ExampleController(new RestTemplate());
    }

    @Test
    public void shouldSaveSubmittedExample() throws InterruptedException {
        Map<String, Object> example = new HashMap<>();
        example.put("message", "something");

        exampleController.save(example);

        Thread.sleep(1000);

        SearchResponse exampleResponses = client.prepareSearch("example").setTypes("response").execute().actionGet();
        assertThat(exampleResponses.getHits().getAt(0).sourceAsString(), equalTo("{\"message\":\"something\"}"));
    }
}
