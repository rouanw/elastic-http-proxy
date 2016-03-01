package example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/example")
public class ExampleController {

    private RestTemplate restTemplate;

    public ExampleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExampleController() {
        this.restTemplate = new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.GET, produces={"application/json"})
    public String list() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9200/example/_search", String.class);
        return response.getBody();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Map<String, Object> example) {
        restTemplate.postForLocation("http://localhost:9200/example/response", example);
    }
}
