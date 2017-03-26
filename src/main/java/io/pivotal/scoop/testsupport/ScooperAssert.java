package io.pivotal.scoop.testsupport;

import io.pivotal.scoop.Scooper;
import org.assertj.core.api.AbstractAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.pivotal.scoop.Scooper.GETTER_PREFIX;
import static io.pivotal.scoop.Scooper.SETTER_PREFIX;

public class ScooperAssert extends AbstractAssert<ScooperAssert, Scooper> {
	public ScooperAssert(Scooper actual) {
		super(actual, ScooperAssert.class);
	}

	public static ScooperAssert assertThat(Scooper scooper) {
		return new ScooperAssert(scooper);
	}

	public ScooperAssert scoopsStrictly(Class src, Class dest) {
		List<SetterMetaData> missingSetters = Arrays.stream(src.getMethods())
			.filter(method -> method.getName().startsWith(GETTER_PREFIX))
			.filter(method -> !method.getDeclaringClass().equals(Object.class))
			.map(method -> new SetterMetaData(method.getName().substring(GETTER_PREFIX.length()), method.getReturnType()))
			.filter(setterMetaData -> {
				try {
					dest.getMethod(SETTER_PREFIX + setterMetaData.getName(), setterMetaData.getReturnType());
					return false;
				} catch (NoSuchMethodException e) {
					return true;
				}
			})
			.collect(Collectors.toList());
		if(!missingSetters.isEmpty()) {
			failWithMessage("Destination class <%s> is missing the following setters: <%s>",
				dest.getName(), missingSetters);
		}
		return this;
	}

	private static class SetterMetaData {
		private final String name;
		private final Class<?> returnType;

		public SetterMetaData(String name, Class<?> returnType) {
			this.name = name;
			this.returnType = returnType;
		}

		public String getName() {
			return name;
		}

		public Class<?> getReturnType() {
			return returnType;
		}

		@Override
		public String toString() {
			return "set" + name + "(" + returnType + ")";
		}
	}
}
