package com.neuralfly.specification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuralfly.specification.function.EmailFunctionSpec;
import com.neuralfly.specification.function.RequiredFunctionSpec;
import com.neuralfly.specification.validation.ValidationResult;
import static com.shazam.shazamcrest.matcher.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecificationTest {

    @Test
    public void shouldExecuteFunctionsSpecifiedOnFields() throws NoSuchMethodException, ScriptException, IOException {
        List<Field> fields = Arrays.asList(
                new Field("email", "string",
                        Arrays.asList(new RequiredFunctionSpec(), new EmailFunctionSpec())));
        Specification spec = new Specification("identifier", fields);

        Map<String, List<ValidationResult>> actual = spec.execute("{\"email\": \"abc@xyz.com\"}");
        Map<String, List<ValidationResult>> expected = new HashMap<>();
        expected.put("email", Arrays.asList(
                new ValidationResult("success", "Validation Successful, field: email, function: required"),
                new ValidationResult("success", "Validation Successful, field: email, function: email")));

        assertThat(actual, sameBeanAs(expected));
    }

    @Test
    public void shouldExecuteFunctionsSpecifiedOnFieldsWithError() throws NoSuchMethodException, ScriptException, IOException {
        List<Field> fields = Arrays.asList(
                new Field("email", "string",
                        Arrays.asList(new RequiredFunctionSpec(), new EmailFunctionSpec())));
        Specification spec = new Specification("identifier", fields);

        Map<String, List<ValidationResult>> actual = spec.execute("{\"email\": \"abc@xyzcom\"}");
        Map<String, List<ValidationResult>> expected = new HashMap<>();
        expected.put("email", Arrays.asList(
                new ValidationResult("success", "Validation Successful, field: email, function: required"),
                new ValidationResult("failure", "Failed Validation, field: email, function: email")));

        assertThat(actual, sameBeanAs(expected));
    }

    @Test
    public void shouldExecuteFunctionsSpecifiedOnFieldsWithMultipleErrors() throws NoSuchMethodException, ScriptException, IOException {
        List<Field> fields = Arrays.asList(
                new Field("email", "string",
                        Arrays.asList(new RequiredFunctionSpec(), new EmailFunctionSpec())));
        Specification spec = new Specification("identifier", fields);

        Map<String, List<ValidationResult>> actual = spec.execute("{}");
        Map<String, List<ValidationResult>> expected = new HashMap<>();
        expected.put("email", Arrays.asList(
                new ValidationResult("failure", "Failed Validation, field: email, function: required"),
                new ValidationResult("failure", "Failed Validation, field: email, function: email")));

        assertThat(actual, sameBeanAs(expected));
    }
}
