	private static TestSource toTestSource(Description description) {
		Class<?> testClass = description.getTestClass();
		if (testClass != null) {
			String methodName = description.getMethodName();
			if (methodName != null) {
				MethodSource methodSource = toMethodSource(testClass, methodName);
				if (methodSource != null) {
					return methodSource;
				}
			}
			return ClassSource.from(testClass);
		}
		return null;
	}
