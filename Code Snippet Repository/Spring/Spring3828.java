	@Test
	public void handleExceptionWithCustomExceptionHandler() {
		Method m = ReflectionUtils.findMethod(TestBean.class, "failWithVoid");
		TestableAsyncUncaughtExceptionHandler exceptionHandler =
				new TestableAsyncUncaughtExceptionHandler();
		BeanDefinition processorDefinition = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
		processorDefinition.getPropertyValues().add("exceptionHandler", exceptionHandler);

		ConfigurableApplicationContext context = initContext(processorDefinition);
		ITestBean testBean = context.getBean("target", ITestBean.class);

		assertFalse("Handler should not have been called", exceptionHandler.isCalled());
		testBean.failWithVoid();
		exceptionHandler.await(3000);
		exceptionHandler.assertCalledWith(m, UnsupportedOperationException.class);
	}
