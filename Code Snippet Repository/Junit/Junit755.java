	private static MethodSource toMethodSource(Class<?> testClass, String methodName) {
		if (methodName.contains("[") && methodName.endsWith("]")) {
			// special case for parameterized tests
			return toMethodSource(testClass, methodName.substring(0, methodName.indexOf("[")));
		}
		else {
			List<Method> methods = findMethods(testClass, where(Method::getName, isEqual(methodName)));
			return (methods.size() == 1) ? MethodSource.from(getOnlyElement(methods)) : null;
		}
	}
