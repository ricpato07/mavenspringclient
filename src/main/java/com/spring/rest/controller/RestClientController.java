package com.spring.rest.controller;

import com.spring.rest.domain.Person;
import com.spring.rest.domain.PersonList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author USUARIO
 */
@Controller
public class RestClientController {

    private final String api = "http://localhost:8084/mavenspringrest/agenda";

    @RequestMapping(value = "agenda/getall", method = RequestMethod.GET)
    public String getAll(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        final String uri = api + "/persons";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        try {
            ResponseEntity<PersonList> result = restTemplate.exchange(uri, HttpMethod.GET, entity, PersonList.class);
//            System.out.println("result:" + result.getStatusCode());
            if (result.getStatusCode() == HttpStatus.OK) {
                model.addAttribute("persons", result.getBody().getData());
            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return "personpage";
    }

    @RequestMapping(value = "agenda/add")
    public String add(Model model, @ModelAttribute("Person") Person person) {
        try {
            System.out.println("getId:" + person.getId());
            if (person.getId() != null) {
                return update(model, person);
            }
            RestTemplate restTemplate = new RestTemplate();
            final String uri = api + "/person";
            System.out.println("first name" + person.getFirstName());
            String result = restTemplate.postForObject(uri, person, String.class);
            System.out.println("result");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model);
    }

    @RequestMapping(value = "agenda/get/{id}", method = RequestMethod.GET)
    public String get(Model model, @PathVariable("id") Long id) {
        System.out.println("entró a get");
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String uri = api + "/person/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            Person person = restTemplate.getForObject(uri, Person.class, params);
            model.addAttribute("id", person.getId());
            model.addAttribute("firstname", person.getFirstName());
            model.addAttribute("lastname", person.getLastName());
            model.addAttribute("money", person.getMoney());
            model.addAttribute("edit", 1);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model);
    }

    @RequestMapping(value = "agenda/clean", method = RequestMethod.GET)
    public String clean(Model model) {
        System.out.println("entró a clean");
        try {
            model.addAttribute("id", null);
            model.addAttribute("firstname", null);
            model.addAttribute("lastname", null);
            model.addAttribute("money", null);

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model);
    }

    @RequestMapping(value = "agenda/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        System.out.println("entró a delete");
        try {
            System.out.println("id:" + id);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Person> entity = new HttpEntity<Person>(headers);

            final String uri = api + "/person/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class, params);
            System.out.println("result:" + result.getStatusCode());

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model);
    }

    @RequestMapping(value = "agenda/update")
    public String update(Model model, @ModelAttribute("Person") Person person) {
        System.out.println("entró a update");

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(person, headers);

            final String uri = api + "/person/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(person.getId()));

            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class, params);
            System.out.println("result:" + result.getStatusCode());

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model);
    }

}
