package com.github.phillipkruger.jaxblib.example;

import com.github.phillipkruger.jaxblib.example.util.BadJAXBUtil;
import com.github.phillipkruger.jaxblib.example.data.DummyData;
import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import com.github.phillipkruger.jaxblib.example.util.GoodJAXBUtil;
import com.github.phillipkruger.jaxblib.example.util.JAXBUtil;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.java.Log;

@Log
public class PlainJavaApplication {
    
    private final DummyData dummyData = new DummyData(100000);
    
    private final BadJAXBUtil bad = new BadJAXBUtil();
    private final GoodJAXBUtil good = new GoodJAXBUtil();
    
    public void runBadBatch(){
        log.info(run(bad,dummyData.getMemberships()));
    }
    
    public void runGoodBatch(){
        log.info(run(good,dummyData.getMemberships()));
    }
    
    public static void main(String[] args){
        PlainJavaApplication r = new PlainJavaApplication();
        Scanner in = new Scanner(System.in);
        logWelcomeMessage();
        boolean running = true;
        while(running){
            String s = in.nextLine();
            if(s.trim().equalsIgnoreCase("1")){
                System.gc();
                r.runBadBatch();
            }else if(s.trim().equalsIgnoreCase("2")){
                System.gc();
                r.runGoodBatch();
            }else if(s.trim().equalsIgnoreCase("x")){
                System.exit(0);
            }else{
                logWelcomeMessage();
            }
            log.severe(s);
        }
    }
    
    private static void logWelcomeMessage(){
        log.info("Welcome to the JAXB test.\n"
                + "Enter to :\n"
                + " 1 = Run bad batch example\n"
                + " 2 = Run good batch example\n"
                + " x = Exit");
    }
    
    private String run(JAXBUtil util,List<Membership> members){
        StringWriter report = new StringWriter();
        
        report.append("\n========================================================================\n");
        report.append("Testing " + members.size() + " with " + util.getName() + " util\n\n");
        
        List<String> xmls = new ArrayList<>();
        // Marshal
        final long startmarshal = System.currentTimeMillis();
        for(Membership membership:members){
            xmls.add(util.marshal(membership));
        }
        final long timemarshal = System.currentTimeMillis() - startmarshal;
        
        report.append("Marshal took: " + timemarshal + " ms\n");
        
        // Unmarshal
        final long startunmarshal = System.currentTimeMillis();
        for(String xml:xmls){
            util.unmarshal(xml);
        }
        final long timeunmarshal = System.currentTimeMillis() - startunmarshal;
        report.append("Unmarshal took: " + timeunmarshal + " ms\n\n");
        
        report.append("========================================================================\n");
        
        xmls.clear();
        
        return report.toString();
    }
}