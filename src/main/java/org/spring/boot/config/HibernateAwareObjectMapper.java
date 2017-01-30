package org.spring.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * ObjectMapper for JSON serialization/deserialization
 * Created by Aleksey Stoyokha on 28.01.17.
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
    /**
     * Register Hibernate module.
     */
    public HibernateAwareObjectMapper() {
        registerModule(new Hibernate5Module());
    }
}
