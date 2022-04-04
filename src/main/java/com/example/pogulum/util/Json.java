package com.example.pogulum.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Json {

    private static Json instance;

    public static Json getInstance(){
        instance = instance == null ? new Json() : instance;
        return instance;
    }

    private Json(){
        this.mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        this.jsonWriter = mapper.writer(new DefaultPrettyPrinter());
    }

    private final ObjectMapper mapper;
    private final ObjectWriter jsonWriter;

    /*
     * Converting Object to Pretty JSON String (2 additional methods for specifying Type)
     */

    public <T> String stringify(Object value, Class<T> type) throws JsonProcessingException {
        return jsonWriter.forType(type).writeValueAsString(value);
    }

    public <T> String stringify(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    public <T> String stringifyList(Collection<T> value, Class<T> type) throws JsonProcessingException {
        ArrayList<T> list = new ArrayList<T>(value);
        final CollectionLikeType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, type);
        return jsonWriter.forType(listType).writeValueAsString(list);
    }

    public <T> String stringifyList(Collection<T> value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    /*
     * Parsing from String to Object
     */

    public <T> T parse(String jsonString, Class<T> type) throws IOException {
        return mapper.readValue(jsonString, type);
    }

    public <T> T parseList(String jsonString, Class<T> type) throws IOException {
        final CollectionLikeType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, type);
        return mapper.readValue(jsonString, listType);
    }

}
