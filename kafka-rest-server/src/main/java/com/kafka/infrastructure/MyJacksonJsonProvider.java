package com.kafka.infrastructure;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
 

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

@Provider
//@Produces({MediaType.APPLICATION_JSON})
//@Consumes(MediaType.APPLICATION_JSON)
//@Singleton
public class MyJacksonJsonProvider implements ContextResolver<ObjectMapper> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {

        SerializationConfig.Feature feature = SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS;
        MAPPER.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, true);
        //MAPPER.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }
public MyJacksonJsonProvider() {
System.out.println("Instantiate MyJacksonJsonProvider");
}
    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("MyJacksonProvider.getContext() called with type: "+type);
        return MAPPER;
    }
}