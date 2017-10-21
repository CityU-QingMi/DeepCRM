	protected void assertException(Method method, String pointcut, String returning, String throwing,
			Class<?> exceptionType, String message) {

		AspectJAdviceParameterNameDiscoverer discoverer = new AspectJAdviceParameterNameDiscoverer(pointcut);
		discoverer.setRaiseExceptions(true);
		discoverer.setReturningName(returning);
		discoverer.setThrowingName(throwing);

		try {
			discoverer.getParameterNames(method);
			fail("Expecting " + exceptionType.getName() + " with message '" + message + "'");
		}
		catch (RuntimeException expected) {
			assertEquals("Expecting exception of type " + exceptionType.getName(),
					exceptionType, expected.getClass());
			assertEquals("Exception message does not match expected", message, expected.getMessage());
		}
	}
