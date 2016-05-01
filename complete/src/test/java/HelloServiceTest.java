import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import hello.Application;
import hello.Greeting;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class HelloServiceTest {

    RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}")
    int port;

    @Test
    public void simpleGetTest() {
        
        URI url = URI.create(String.format("http://localhost:%s/greeting/?name=Test", port));
        Greeting greeting = restTemplate.getForObject(url, Greeting.class);
        
        Assert.assertEquals("Hello, Test!", greeting.getContent());
    }
    
    @Test
    public void simpleGetDefaultValueTest() {
        
        URI url = URI.create(String.format("http://localhost:%s/greeting", port));
        Greeting greeting = restTemplate.getForObject(url, Greeting.class);
        
        // 'World' is default name
        Assert.assertEquals("Hello, World!", greeting.getContent());
    }
    
    @Test
    public void simplePostTest() {
        
        URI url = URI.create(String.format("http://localhost:%s/greeting", port));
        
        ResponseEntity<Greeting> responseEntity = restTemplate.postForEntity(url, "Test", Greeting.class);
        
        Assert.assertEquals("Hello, Test!", responseEntity.getBody().getContent());
    }
}
