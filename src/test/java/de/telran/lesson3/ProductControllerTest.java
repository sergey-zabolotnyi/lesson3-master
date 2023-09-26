package de.telran.lesson3;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void getProductId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        String url = "http://localhost:" + port + "/product/1";
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET,entity, String.class);

        String expected = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Banana\",\n" +
                "        \"price\": 150.0\n" +
                "    }";
        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

}
