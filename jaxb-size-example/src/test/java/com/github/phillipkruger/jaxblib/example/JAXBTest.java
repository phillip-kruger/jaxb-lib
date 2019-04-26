package com.github.phillipkruger.jaxblib.example;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import com.github.phillipkruger.jaxblib.example.util.GoodJAXBUtil;
import java.io.InputStream;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log
public class JAXBTest {

    private static final String BIG_FILE = "large.xml";
    
    private final GoodJAXBUtil util = new GoodJAXBUtil();
    
    @Test
    public void testResourcesAvailable() {
        assertNotNull(getClass().getResource("/" + BIG_FILE),BIG_FILE + " file missing");
    }
    
    @Test
    public void testGoodUtilDefault(){
        InputStream xmlStream = getClass().getResourceAsStream("/" + BIG_FILE);
        
        OutOfMemoryError thrown = assertThrows(OutOfMemoryError.class,
                    () -> util.unmarshal(xmlStream), "Expected OutOfMemoryError() to throw, but it didn't");
        
        thrown.printStackTrace();
        assertTrue(thrown.getMessage().contains("Java heap space"));           
    }
    
    @Test
    public void testGoodUtilSax(){
        InputStream xmlStream = getClass().getResourceAsStream("/" + BIG_FILE);
        Membership membership = util.unmarshalWithSAX(xmlStream);
        assertNotNull(membership);
    }
}
