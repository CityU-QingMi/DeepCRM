	@Override
	@SuppressWarnings("")
	protected Statement withPotentialTimeout(FrameworkMethod frameworkMethod, Object testInstance, Statement next) {
		Statement statement = null;
		long springTimeout = getSpringTimeout(frameworkMethod);
		long junitTimeout = getJUnitTimeout(frameworkMethod);
		if (springTimeout > 0 && junitTimeout > 0) {
			String msg = String.format("Test method [%s] has been configured with Spring's @Timed(millis=%s) and " +
							"JUnit's @Test(timeout=%s) annotations, but only one declaration of a 'timeout' is " +
							"permitted per test method.", frameworkMethod.getMethod(), springTimeout, junitTimeout);
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
		else if (springTimeout > 0) {
			statement = new SpringFailOnTimeout(next, springTimeout);
		}
		else if (junitTimeout > 0) {
			statement = FailOnTimeout.builder().withTimeout(junitTimeout, TimeUnit.MILLISECONDS).build(next);
		}
		else {
			statement = next;
		}

		return statement;
	}
