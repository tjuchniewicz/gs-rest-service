package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController implements GreetingService {

    private static final String template = "Hello, %s!";
    
    private final AtomicLong counter = new AtomicLong();
    
    private static Logger log = LoggerFactory.getLogger(GreetingController.class);

    @Override
    public Greeting greetingByGet(
        /** 
        * Why do I need to duplicate @RequestParam annotation on implementation to make it work ??? 
        * Otherwise GET default value is not used. 
        */ 
        @RequestParam(value="name", defaultValue="World")
        String name) {
    
            log.info("Request GET param: {}", name);
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @Override
    public Greeting greetingByPost(
        /** 
         * Why do I need to duplicate @RequestBody annotation on implementation to make it work ??? 
         * Otherwise POST payload is always null. 
         */ 
        @RequestBody 
        String name) {
        
            log.info("Request POST body: {}", name);
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
