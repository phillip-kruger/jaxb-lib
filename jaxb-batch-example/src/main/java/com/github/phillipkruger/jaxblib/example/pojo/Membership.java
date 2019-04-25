package com.github.phillipkruger.jaxblib.example.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Membership {
    
    @XmlAttribute
    private int membershipId;
    
    @XmlElement
    private Person owner;
    
    @XmlAttribute        
    private Type type;
    
    @XmlElement
    private List<Crap> craps = new ArrayList<>();
}