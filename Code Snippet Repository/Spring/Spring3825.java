	@Test
	public void handleExceptionWithFuture() {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(ConfigWithExceptionHandler.class);
		ITestBean testBean = context.getBean("target", ITestBean.class);

		TestableAsyncUncaughtExceptionHandler exceptionHandler =
				context.getBean("exceptionHandler", TestableAsyncUncaughtExceptionHandler.class);
		assertFalse("handler should not have been called yet", exceptionHandler.isCalled());
		Future<Object> result = testBean.failWithFuture();
		assertFutureWithException(result, exceptionHandler);
	}
