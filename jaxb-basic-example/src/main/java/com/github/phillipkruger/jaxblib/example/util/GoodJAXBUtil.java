package com.github.phillipkruger.jaxblib.example.util;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class GoodJAXBUtil implements JAXBUtil {

    @Override
    public String marshal(Membership memberships){
        StringWriter stringWriter = new StringWriter();
        try {
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(memberships, stringWriter);
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
            Unmarshaller u = context.createUnmarshaller();
            return (Membership)u.unmarshal(new StringReader(xml));
        }catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public String getName() {
        return "Good";
    }
    
    private static JAXBContext context;
    static{
        try {
            context = JAXBContext.newInstance(Membership.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
