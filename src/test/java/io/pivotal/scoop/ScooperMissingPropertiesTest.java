package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithThreeProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScooperMissingPropertiesTest {

	public static class SimpleScooper extends Scooper {
	}

	@Test
	@DisplayName("scoop() : reports properties from source missing on destination.")
	public void ignoreMissingPropertiesOnDest() {
		SimpleScooper subject = new SimpleScooper();
		FooBeanWithThreeProperties foo = new FooBeanWithThreeProperties();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		foo.setBaz("baz");
		foo.setRee("ree");
		foo.setQux("qux");
		assertThatThrownBy(() -> subject.scoop(foo, bar)).hasMessageContaining("Qux");
	}

	@Test
	@DisplayName("scoop() : ignores properties on destination missing on source.")
	public void ignoreMissingPropertiesOnSource() {
		SimpleScooper subject = new SimpleScooper();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		FooBeanWithThreeProperties foo = new FooBeanWithThreeProperties();
		bar.setBaz("baz");
		bar.setRee("ree");
		subject.scoop(bar, foo);
		assertEquals("baz", foo.getBaz());
		assertEquals("ree", foo.getRee());
	}
}
