package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithThreeProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterMissingPropertiesTest {

	public static class SimpleConverter extends Converter {
	}

	@Test
	@DisplayName("convert() : ignores properties from source missing on destination.")
	public void ignoreMissingProperties() {
		SimpleConverter subject = new SimpleConverter();
		FooBeanWithThreeProperties foo = new FooBeanWithThreeProperties();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		foo.setBaz("baz");
		foo.setRee("ree");
		foo.setQux("qux");
		subject.convert(foo, bar);
		assertEquals("baz", bar.getBaz());
		assertEquals("ree", bar.getRee());
	}

	@Test
	public void convert_xxx() {
	}
}
