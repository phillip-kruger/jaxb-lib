package com.github.phillipkruger.jaxblib.example.util;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BadJAXBUtil implements JAXBUtil {

    @Override
    public String marshal(Membership membership){
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Membership.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(membership, stringWriter);
            String xml = stringWriter.toString();
            stringWriter.close();
            return xml;
        } catch (JAXBException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Membership unmarshal(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Membership.class);
            Unmarshaller u = context.createUnmarshaller();
            return (Membership)u.unmarshal(new StringReader(xml));
        }catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Membership unmarshal(InputStream xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Membership.class);
            Unmarshaller u = context.createUnmarshaller();
            return (Membership)u.unmarshal(xml);
        }catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public String getName() {
        return "Bad";
    }
    
}
