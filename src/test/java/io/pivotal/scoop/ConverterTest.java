package io.pivotal.scoop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
	private SimpleConverter subject;
	private FooBean foo;
	private BarBean bar;

	public static class FooBean {
		private String baz;
		private String ree;

		public String getBaz() {
			return baz;
		}

		public void setBaz(String baz) {
			this.baz = baz;
		}

		public String getRee() {
			return ree;
		}

		public void setRee(String ree) {
			this.ree = ree;
		}
	}

	public static class BarBean {
		private String baz;
		private String ree;

		public String getBaz() {
			return baz;
		}

		public void setBaz(String baz) {
			this.baz = baz;
		}

		public String getRee() {
			return ree;
		}

		public void setRee(String ree) {
			this.ree = ree;
		}
	}

	public static class SimpleConverter extends Converter {
	}

	@BeforeEach
	public void setUpConverter() {
		subject = new SimpleConverter();
	}

	@Test
	public void copiesValuesOfSameNamedProperties() {
		foo = new FooBean();
		bar = new BarBean();
		foo.setBaz("baz");
		foo.setRee("ree");
		subject.convert(foo, bar);
		assertEquals("baz", bar.getBaz());
		assertEquals("ree", bar.getRee());
	}
}
