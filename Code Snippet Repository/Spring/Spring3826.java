	@Test
	public void handleExceptionWithListenableFuture() {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(ConfigWithExceptionHandler.class);
		ITestBean testBean = context.getBean("target", ITestBean.class);

		TestableAsyncUncaughtExceptionHandler exceptionHandler =
				context.getBean("exceptionHandler", TestableAsyncUncaughtExceptionHandler.class);
		assertFalse("handler should not have been called yet", exceptionHandler.isCalled());
		Future<Object> result = testBean.failWithListenableFuture();
		assertFutureWithException(result, exceptionHandler);
	}
