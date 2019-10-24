package com.neuralfly.specification;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

public class SpecificationBuilder {
    private final String jsonSpec;
    private ObjectReader reader;

    public SpecificationBuilder(String jsonSpec){
        this.jsonSpec = jsonSpec;
        this.init();
    }

    @PostConstruct
    protected void init(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        /*SimpleModule module = new SimpleModule();
        module.addDeserializer(Specification.class, new SpecificationDeserializer());
        objectMapper.registerModule(module);*/
        this.reader = objectMapper
                .readerFor(Specification.class)
                .with(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public Optional<Specification> build() throws IOException {
        if(jsonSpec == null || "{}".equals(jsonSpec) || "".equals(jsonSpec)){
            return Optional.empty();
        }
        return Optional.ofNullable(reader.readValue(jsonSpec));
    }
}
