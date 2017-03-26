package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithTwoProperties;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScooperIgnoredPropertiesTest {
	static class IgnoringScooper extends Scooper {
		IgnoringScooper() {
			this.ignoredProperties = Lists.newArrayList("Ree");
		}
	}

	@Test
	@DisplayName("scoop() : skips scooping properties listed as 'ignored'.")
	public void ignoreMissingPropertiesOnDest() {
		IgnoringScooper subject = new IgnoringScooper();
		FooBeanWithTwoProperties foo = new FooBeanWithTwoProperties();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		foo.setBaz("baz");
		foo.setRee("ree");
		subject.scoop(foo, bar);
		assertThat(bar.getBaz()).isEqualTo("baz");
		assertThat(bar.getRee()).isNull();
	}
}
