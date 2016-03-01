package example;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/animal", produces={"application/json"})
public class AnimalController {

    @ApiOperation(value = "getAnimal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Animal.class),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.GET, produces={"application/json"})
    public Animal get() {
        return new Animal("dog", "woof");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Animal animal) {
        System.out.println(animal.toString());
    }
}
