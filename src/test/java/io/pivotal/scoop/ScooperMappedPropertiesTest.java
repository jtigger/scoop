package io.pivotal.scoop;

import io.pivotal.scoop.samplebeans.BarBeanWithTwoProperties;
import io.pivotal.scoop.samplebeans.FooBeanWithThreeProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScooperMappedPropertiesTest {
	private static class MappingScooper extends Scooper {
		public void mapQuxTo_blahblahblah(FooBeanWithThreeProperties src, BarBeanWithTwoProperties dest) {
			dest.setBaz(dest.getBaz() + "+" + src.getQux());
			dest.setRee(dest.getRee() + "+" + src.getQux());
		}
	}

	@Test
	@DisplayName("scoop : when a mapping method is present, uses that instead")
	public void usesDefinedMappingMethods() {
		MappingScooper subject = new MappingScooper();
		FooBeanWithThreeProperties foo = new FooBeanWithThreeProperties();
		BarBeanWithTwoProperties bar = new BarBeanWithTwoProperties();
		foo.setBaz("baz");
		foo.setRee("ree");
		foo.setQux("qux");
		subject.scoop(foo, bar);
		assertThat(bar.getBaz()).isEqualTo("baz+qux");
		assertThat(bar.getRee()).isEqualTo("ree+qux");
	}
}
