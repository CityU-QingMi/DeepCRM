	@Test
	public void exceptionHandlerThrowsUnexpectedException() {
		Method m = ReflectionUtils.findMethod(TestBean.class, "failWithVoid");
		TestableAsyncUncaughtExceptionHandler exceptionHandler =
				new TestableAsyncUncaughtExceptionHandler(true);
		BeanDefinition processorDefinition = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
		processorDefinition.getPropertyValues().add("exceptionHandler", exceptionHandler);
		processorDefinition.getPropertyValues().add("executor", new DirectExecutor());

		ConfigurableApplicationContext context = initContext(processorDefinition);
		ITestBean testBean = context.getBean("target", ITestBean.class);

		assertFalse("Handler should not have been called", exceptionHandler.isCalled());
		try {
			testBean.failWithVoid();
			exceptionHandler.assertCalledWith(m, UnsupportedOperationException.class);
		}
		catch (Exception e) {
			fail("No unexpected exception should have been received");
		}
	}
