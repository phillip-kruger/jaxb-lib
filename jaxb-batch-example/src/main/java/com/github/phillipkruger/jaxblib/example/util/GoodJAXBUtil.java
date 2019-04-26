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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

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
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Membership)unmarshaller.unmarshal(new StringReader(xml));
        }catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Membership unmarshal(InputStream xml) {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Membership)unmarshaller.unmarshal(xml);
        }catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Membership unmarshalWithSAX(InputStream xml){
        try {
            InputSource inputSource = new InputSource(xml);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            spf.setValidating(true);
        
            SAXParser saxParser = spf.newSAXParser();
            saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            
            XMLReader xmlReader = saxParser.getXMLReader();
            SAXSource source = new SAXSource(xmlReader, inputSource);
            
            Unmarshaller u = context.createUnmarshaller();

            return (Membership)u.unmarshal(source);
        }catch (ParserConfigurationException | SAXException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
   
    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

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
