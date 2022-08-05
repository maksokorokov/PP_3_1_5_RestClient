package org.example;

import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final String URL ="http://94.198.50.185:7081/api/users";
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void getAllUser(){


        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        System.out.println(result);
        headers.add("Cookie", String.valueOf(response.getHeaders().getFirst(HttpHeaders.SET_COOKIE)));

    }

    public void saveUser(User user){
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }


    public void updateUser(User user){
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());
        System.out.println("Изменен работник с id=3");
    }

    public void deleteUser(long id){
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL+"/"+id, HttpMethod.DELETE, entity, String.class);
        System.out.println(response.getBody());
        System.out.println("Удален работник с id=3");
    }
}
