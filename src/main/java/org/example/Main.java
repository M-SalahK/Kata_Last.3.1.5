package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {

    static String url = "http://94.198.50.185:7081/api/users";
    static RestTemplate restTemplate = new RestTemplate();
    static HttpHeaders headers = new HttpHeaders();
    static HttpEntity<User> httpEntity = new HttpEntity<>(headers);

    public static void main(String[] args) {

        User user = new User(3L, "James", "Brown", (byte) 12);
        User user1 = new User(3L, "Thomas", "Shelby", (byte) 12);

        getUser();
        postRequest(user);
        putRequest(user1);
        delete(3L);
    }

    public static void getUser() {

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        headers.set("Cookie", response.getHeaders().getFirst("Set-Cookie"));
        System.out.println(response.getBody() + headers.getFirst("Set-Cookie"));
    }

    public static void postRequest(User user) {

        HttpEntity<User> requestUser = new HttpEntity<>(user, headers);
        System.out.println(restTemplate.exchange(url, HttpMethod.POST, requestUser, String.class).getBody());
    }

    public static void putRequest(User user) {

        HttpEntity<User> requestUser = new HttpEntity<>(user, headers);
        System.out.println(restTemplate.exchange(url, HttpMethod.PUT, requestUser, String.class).getBody());
    }

    public static void delete(Long id) {

        HttpEntity<User> requestUser = new HttpEntity<>(headers);
        String removeUrl = url + "/" + id;
        System.out.println(restTemplate.exchange(removeUrl, HttpMethod.DELETE, requestUser, String.class).getBody());
    }

}
