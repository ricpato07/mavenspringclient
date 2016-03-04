package com.spring.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@XmlRootElement(name="person")
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Double money;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(Double money) {
        this.money = money;
    }
}
