package com.github.phillipkruger.jaxblib.example.util;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import java.io.InputStream;

public interface JAXBUtil {
    public String marshal(Membership memberships);
    public Membership unmarshal(String xml);
    public Membership unmarshal(InputStream xml);
    public String getName();
}
