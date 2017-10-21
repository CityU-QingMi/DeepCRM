	protected void assertParameterNames(Method method, String pointcut, String returning, String throwing,
			String[] parameterNames) {

		assertEquals("bad test specification, must have same number of parameter names as method arguments",
				method.getParameterCount(), parameterNames.length);

		AspectJAdviceParameterNameDiscoverer discoverer = new AspectJAdviceParameterNameDiscoverer(pointcut);
		discoverer.setRaiseExceptions(true);
		discoverer.setReturningName(returning);
		discoverer.setThrowingName(throwing);
		String[] discoveredNames = discoverer.getParameterNames(method);

		String formattedExpectedNames = format(parameterNames);
		String formattedActualNames = format(discoveredNames);

		assertEquals("Expecting " + parameterNames.length + " parameter names in return set '" +
				formattedExpectedNames + "', but found " + discoveredNames.length +
				" '" + formattedActualNames + "'",
				parameterNames.length, discoveredNames.length);

		for (int i = 0; i < discoveredNames.length; i++) {
			assertNotNull("Parameter names must never be null", discoveredNames[i]);
			assertEquals("Expecting parameter " + i + " to be named '" +
					parameterNames[i] + "' but was '" + discoveredNames[i] + "'",
					parameterNames[i], discoveredNames[i]);
		}
	}
