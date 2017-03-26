package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithTwoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScooperTest {

	public static class SimpleScooper extends Scooper {
	}

	@Test
	@DisplayName("scoop() : copies values of same-named properties.")
	public void copiesSameNamedProperties() {
		SimpleScooper subject = new SimpleScooper();
		FooBeanWithTwoProperties foo = new FooBeanWithTwoProperties();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		foo.setBaz("baz");
		foo.setRee("ree");
		subject.scoop(foo, bar);
		assertEquals("baz", bar.getBaz());
		assertEquals("ree", bar.getRee());
	}
}
