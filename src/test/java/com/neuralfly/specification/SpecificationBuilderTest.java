package com.neuralfly.specification;

import com.neuralfly.specification.function.PatternFunctionSpec;
import static com.shazam.shazamcrest.matcher.Matchers.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class SpecificationBuilderTest {

    @Test
    public void createEmptySpecificationFromEmptyJson() throws IOException {
        String jsonSpec = "{}";
        SpecificationBuilder specBuilder = new SpecificationBuilder(jsonSpec);
        Optional<Specification> spec = specBuilder.build();
        assertEquals(Optional.empty(), spec);
    }

    @Test
    public void createSpecificationFromJson() throws IOException {
        String jsonSpec = "{\"identifier\": \"xyz\"}";
        SpecificationBuilder specBuilder = new SpecificationBuilder(jsonSpec);
        Optional<Specification> spec = specBuilder.build();
        assertThat(spec.get(), sameBeanAs(new Specification("xyz", null)));
    }

    @Test
    public void createSpecificationWithField() throws IOException {
        String jsonSpec = "{\"identifier\": \"xyz\", \"fields\": " +
                "[{\"name\": \"email\", \"type\": \"string\", \"functionSpecs\":" +
                "[{\"type\":\"pattern\", \"pattern\": \"\\\\d+\"}]}]}";
        SpecificationBuilder specBuilder = new SpecificationBuilder(jsonSpec);
        Specification actual = specBuilder.build().get();
        Specification expected = new Specification("xyz", Arrays.asList(new Field("email", "string",
                Arrays.asList(new PatternFunctionSpec("\\d+")))));
        assertThat(actual, sameBeanAs(expected));
    }
}
