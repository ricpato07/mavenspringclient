package com.spring.rest.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@XmlRootElement(name="persons")
public class PersonList {
    private List<Person> data;

    /**
     * @return the data
     */
    public List<Person> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<Person> data) {
        this.data = data;
    }
}
