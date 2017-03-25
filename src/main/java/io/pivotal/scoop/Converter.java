package io.pivotal.scoop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Converter {

	public static final String GETTER_PREFIX = "get";
	public static final String SETTER_PREFIX = "set";

	public void convert(Object src, Object dest) {
		Method[] methodsOnSource = src.getClass().getMethods();
		Arrays.stream(methodsOnSource)
			.filter(method -> method.getName().startsWith(GETTER_PREFIX))
			.filter(method -> !method.getDeclaringClass().equals(Object.class))
			.map(method -> method.getName().substring(GETTER_PREFIX.length()))
			.forEach(propertyName -> {
				try {
					Method getter = src.getClass().getMethod(GETTER_PREFIX + propertyName);
					Method setter = null;
					try {
						setter = dest.getClass().getMethod(SETTER_PREFIX + propertyName, getter.getReturnType());
					} catch (NoSuchMethodException e) {
						// quietly skip these
					}
					if (setter != null) {
						Object value = getter.invoke(src);
						setter.invoke(dest, value);
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			});
	}
}
