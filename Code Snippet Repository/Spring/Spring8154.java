	private static TestContext copyTestContext(TestContext testContext) {
		Constructor<? extends TestContext> constructor = ClassUtils.getConstructorIfAvailable(testContext.getClass(),
			testContext.getClass());

		if (constructor != null) {
			try {
				ReflectionUtils.makeAccessible(constructor);
				return constructor.newInstance(testContext);
			}
			catch (Exception ex) {
				if (logger.isInfoEnabled()) {
					logger.info(String.format("Failed to invoke copy constructor for [%s]; " +
							"concurrent test execution is therefore likely not supported.",
							testContext), ex);
				}
			}
		}

		// fallback to original instance
		return testContext;
	}
