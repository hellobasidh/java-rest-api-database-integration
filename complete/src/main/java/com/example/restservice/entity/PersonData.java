package com.example.restservice.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonData {

    @Id
    private String personname;
    private Integer age;
    private Integer id;
    private String documentnumber;
    private String selfimage;
    private String documentimage;
    private String email;

    public String getPersonName() {
        return personname;
    }

    public void setPersonName(String personname) {
        this.personname = personname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentnumber;
    }

    public void setDocumentNumber(String documentnumber) {
        this.documentnumber = documentnumber;
    }

    public String getDocumentImage() {
        return documentimage;
    }

    public void setDocumentImage(String documentimage) {
        this.documentimage = documentimage;
    }

    public String getSelfImage() {
        return selfimage;
    }

    public void setSelfImage(String selfimage) {
        this.selfimage = selfimage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + personname + '\'' + ", age='" + age + '\'' + ", document number='" + documentnumber + '\''
                + ", email='" + email + '\'' + ", Document Image='" + documentimage + '\'' + ", Self Image='" + selfimage + '\'' + '}';
    }
}
