package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController implements GreetingService {

    private static final String template = "Hello, %s!";
    
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Greeting greetingByGet(@RequestParam(value="name", defaultValue="World") String name) {
        
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @Override
    public Greeting greetingByPost(/** Why I need to use it to make it work ??? */ @RequestBody String name) {
        
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
