package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithTwoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

	public static class SimpleConverter extends Converter {
	}

	@Test
	@DisplayName("convert() : copies values of same-named properties.")
	public void copiesSameNamedProperties() {
		SimpleConverter subject = new SimpleConverter();
		FooBeanWithTwoProperties foo = new FooBeanWithTwoProperties();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		foo.setBaz("baz");
		foo.setRee("ree");
		subject.convert(foo, bar);
		assertEquals("baz", bar.getBaz());
		assertEquals("ree", bar.getRee());
	}
}
