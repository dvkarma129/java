package com.empmngt.config;

import com.empmngt.dto.DeptDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import org.hibernate.Hibernate;

public class CustomDepartmentSerializer extends JsonSerializer<DeptDto> {

    @Override
    public void serialize(DeptDto department, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
    	if (!Hibernate.isInitialized(department)) {
            Hibernate.initialize(department);
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", department.getId());
        jsonGenerator.writeStringField("departmentName", department.getDepartmentName());
        jsonGenerator.writeEndObject();
    }
}
