package com.neuralfly.specification.function;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailFunctionSpec.class, name = "email"),
        @JsonSubTypes.Type(value = PatternFunctionSpec.class, name = "pattern"),
        @JsonSubTypes.Type(value = RequiredFunctionSpec.class, name = "required"),
        @JsonSubTypes.Type(value = InlineFunctionSpec.class, name = "inline")
})
public abstract class FunctionSpec {
    public abstract String name();

    public abstract boolean validate(String data, String fieldName, ScriptEngine engine) throws ScriptException, NoSuchMethodException;
}
