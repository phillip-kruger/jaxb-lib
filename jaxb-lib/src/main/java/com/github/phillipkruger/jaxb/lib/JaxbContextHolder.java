package com.github.phillipkruger.jaxb.lib;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * As we only need to create the JAXB context once, we keep it statically here
 *
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
public class JaxbContextHolder {

    private static final Map<String, JAXBContext> CONTEXT_MAP = new ConcurrentHashMap<>();

    public static JAXBContext getJAXBContext(Object ob) {
        final Class<?> c = ob.getClass();
        return getJAXBContext(c);
    }

    public static JAXBContext getJAXBContext(Class<?> c) {
        final String name = c.getName();
        if (CONTEXT_MAP.containsKey(name)) {
            return CONTEXT_MAP.get(name);
        } else {
            try {
                JAXBContext context = JAXBContext.newInstance(c);
                CONTEXT_MAP.put(name, context);
                return context;
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
    }
}