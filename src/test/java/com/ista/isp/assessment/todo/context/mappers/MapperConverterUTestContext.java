package com.ista.isp.assessment.todo.context.mappers;

import com.ista.isp.assessment.todo.mappers.ItemConverter;
import com.ista.isp.assessment.todo.mappers.StatusConverter;
import com.ista.isp.assessment.todo.mappers.StatusEntryConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class MapperConverterUTestContext {

    @Bean
    public ItemConverter itemConverter() {
        return new ItemConverter();
    }

    @Bean
    public StatusConverter statusConverter() {
        return new StatusConverter();
    }

    @Bean
    public StatusEntryConverter statusEntryConverter() {
        return new StatusEntryConverter();
    }
}
