# JAXB Lib.

Small library to help with using JAXB

## Including it in your project

```xml
    <dependency>
        <groupId>com.github.phillip-kruger.jaxb-library</groupId>
        <artifactId>jaxb-lib</artifactId>
        <version>1.0.0</version>
    </dependency>
```

## Using it in your code

### Marshal

```java
    JaxbUtil jaxbUtil = new JaxbUtil();
    byte[] xml = jaxbUtil.marshal(myJAXBObject);
```

### Unmarshal

```java
    JaxbUtil jaxbUtil = new JaxbUtil();
    MyJAXBObject myJAXBObject = jaxbUtil.unmarshal(MyJAXBObject.class,xml);
```

### Getting the XSD for a JAXB Object

```java
    XsdUtil xsdUtil = new XsdUtil();
    String xsd = xsdUtil.getXsd(MyJAXBObject.class);
```
