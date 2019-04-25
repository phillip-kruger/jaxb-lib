package com.github.phillipkruger.jaxblib.example.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private int id;
    @XmlElement
    private List<String> names = new ArrayList<>();
    @XmlElement
    private String surname;
    @XmlElement
    private Gender gender;
    @XmlElement
    private Address address;
}
