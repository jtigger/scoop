package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithThreeProperties;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.pivotal.scoop.testsupport.ScooperAssert.assertThat;

public class ScooperAssertIgnoredPropertiesTest {
	public static class ScooperIgnoringQux extends Scooper {
		public ScooperIgnoringQux() {
			super();
			ignoredProperties = Lists.newArrayList("Qux");
		}
	}

	@Test
	@DisplayName("passes when missing property is ignored.")
	public void passWhenMissingPropertyIsIgnored() {
		ScooperIgnoringQux subject = new ScooperIgnoringQux();
		assertThat(subject).scoopsStrictly(FooBeanWithThreeProperties.class, BarBeanWithTwoProperties.class);
	}

}
