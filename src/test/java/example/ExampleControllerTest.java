package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class ExampleControllerTest {

  @Mock
  private RestTemplate restTemplate;

  @Test
  public void shouldSaveExampleReceived() {
      RestTemplate restTemplate = mock(RestTemplate.class);
      ExampleController exampleController = new ExampleController(restTemplate);
      HashMap example = new HashMap<>();
      example.put("key", "value");
      exampleController.save(example);
      verify(restTemplate).postForLocation("http://localhost:9200/example/response", example);
  }
}
