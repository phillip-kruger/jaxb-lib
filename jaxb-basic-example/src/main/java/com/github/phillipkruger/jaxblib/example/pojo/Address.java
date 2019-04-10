package com.github.phillipkruger.jaxblib.example.pojo;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement
    private int streetNumber = -1;
    @XmlElement
    private String streetName;
    @XmlElement
    private String city;
    @XmlElement
    private String state;
    @XmlElement
    private BigInteger zipCode;
}
