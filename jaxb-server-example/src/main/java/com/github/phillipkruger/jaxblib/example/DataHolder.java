package com.github.phillipkruger.jaxblib.example;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import com.github.phillipkruger.jaxblib.example.data.DummyData;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class DataHolder {
    
    private final DummyData dummyData = new DummyData(100000);
    @Produces
    private List<Membership> memberships;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        this.memberships = dummyData.getMemberships();
    }
    
}
