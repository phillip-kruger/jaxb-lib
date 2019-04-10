package com.github.phillipkruger.jaxblib.example.util;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;

public interface JAXBUtil {
    public String marshal(Membership memberships);
    public Membership unmarshal(String xml);
    public String getName();
}
