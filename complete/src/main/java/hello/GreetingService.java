package hello;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface GreetingService {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greetingByGet(@RequestParam(value="name", defaultValue="World") String name);
    
    
    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public Greeting greetingByPost(@RequestBody String name);
}
