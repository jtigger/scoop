package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithThreeProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.pivotal.scoop.testsupport.ScooperAssert.assertThat;

public class ScooperAssertMappedPropertiesTest {
	private static class MappingScooper extends Scooper {
		public void mapQuxToBar(Object src, Object dest) {
			// actual implementation would go here.  Not needed for this test.
		}
	}

	@Test
	@DisplayName("passes when property missing on destination is explicitly mapped on source.")
	public void passWhenMissingPropertyIsIgnored() {
		MappingScooper subject = new MappingScooper();
		assertThat(subject).scoopsStrictly(FooBeanWithThreeProperties.class, BarBeanWithTwoProperties.class);
	}
}
