package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithThreeProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithTwoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.pivotal.scoop.testsupport.ScooperAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class ScooperTestHelperTest {

	public static class SimpleScooper extends Scooper {
	}

	@Test
	@DisplayName("passes when all properties in source are mapped to dest.")
	public void passWhenAllPropertiesArePresent() {
		SimpleScooper subject = new SimpleScooper();
		assertThat(subject).scoopsStrictly(FooBeanWithTwoProperties.class, BarBeanWithTwoProperties.class);
	}

	@Test
	@DisplayName("fails when destination is missing a property.")
	public void failWhenDestinationIsMissingProperty() {
		SimpleScooper subject = new SimpleScooper();
		try {
			assertThat(subject).scoopsStrictly(FooBeanWithThreeProperties.class, BarBeanWithTwoProperties.class);
			fail("should have failed because setter is missing, but did not.");
		} catch(AssertionError error) {
			assertThat(error.getMessage()).matches(".*missing.*setter.*");
		}
	}
}
