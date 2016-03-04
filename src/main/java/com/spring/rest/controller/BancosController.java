package com.spring.rest.controller;

import com.spring.rest.domain.Banco;
import com.spring.rest.domain.Person;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
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
public class BancosController {

    private final String api = "http://localhost:8084/mavenspringrest/v1";

    @RequestMapping(value = "bancos/getall", method = RequestMethod.GET)
    public String getAll(Model model, @ModelAttribute("Banco") Banco banco) {
        Map<String, String> params = new HashMap<String, String>();
        String uri=null;
        if (banco != null) {
            System.out.println("buscar:" + banco.getSbanco());
            params.put("nombre", banco.getSbanco());
            uri = api + "/bancos?nombre={nombre}";
        }else{
            uri = api + "/bancos";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class, params);
            if (result.getStatusCode() == HttpStatus.OK) {
                List<Banco> bancoslist = mapper.readValue(result.getBody(), List.class);
                model.addAttribute("bancos", bancoslist);
            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return "bancopage";
    }

    @RequestMapping(value = "bancos/add")
    public String add(Model model, @ModelAttribute("Banco") Banco banco) {
        System.out.println("entró a add");
        try {
            System.out.println("getId:" + banco.getIdBanco());
            if (banco.getIdBanco() != null) {
                System.out.println("banco:"+banco.getSbanco());
                return update(model, banco);
            }
            System.out.println("banco:"+banco.getSbanco());
            RestTemplate restTemplate = new RestTemplate();
            final String uri = api + "/bancos";
            System.out.println("uri:"+uri);
            String result = restTemplate.postForObject(uri, banco, String.class);
            
            System.out.println("result");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model, null);
    }

    @RequestMapping(value = "bancos/get/{id}", method = RequestMethod.GET)
    public String get(Model model, @PathVariable("id") Long id) {
        System.out.println("entró a get");
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String uri = api + "/bancos/{id}";
            System.out.println("uri:"+uri);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            System.out.println("id:"+id);
            Banco banco = restTemplate.getForObject(uri, Banco.class, params);
            System.out.println("banco:"+banco.getSbanco());
            model.addAttribute("idBanco", banco.getIdBanco());
            model.addAttribute("sbanco", banco.getSbanco());
            model.addAttribute("edit", 1);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model, null);
    }

    @RequestMapping(value = "bancos/clean", method = RequestMethod.GET)
    public String clean(Model model) {
        System.out.println("entró a clean");
        try {
            model.addAttribute("idBanco", null);
            model.addAttribute("sbanco", null);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model, null);
    }

    @RequestMapping(value = "bancos/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) {
        System.out.println("entró a delete");
        try {
            System.out.println("id:" + id);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Person> entity = new HttpEntity<Person>(headers);

            final String uri = api + "/bancos/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class,params);
            System.out.println("result:" + result.getStatusCode());

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model, null);
    }

    @RequestMapping(value = "bancos/update")
    public String update(Model model, @ModelAttribute("Banco") Banco banco) {
        System.out.println("entró a update");
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(banco, headers);

            final String uri = api + "/bancos/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(banco.getIdBanco()));

            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class, params);
            System.out.println("result:" + result.getStatusCode());

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            e.printStackTrace();
        }
        return getAll(model, null);
    }

}
