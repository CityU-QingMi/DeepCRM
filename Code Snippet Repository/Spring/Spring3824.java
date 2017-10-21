	@Test
	public void configuredThroughNamespace() {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load(new ClassPathResource("taskNamespaceTests.xml", getClass()));
		context.refresh();
		ITestBean testBean = context.getBean("target", ITestBean.class);
		testBean.test();
		testBean.await(3000);
		Thread asyncThread = testBean.getThread();
		assertTrue(asyncThread.getName().startsWith("testExecutor"));

		TestableAsyncUncaughtExceptionHandler exceptionHandler =
				context.getBean("exceptionHandler", TestableAsyncUncaughtExceptionHandler.class);
		assertFalse("handler should not have been called yet", exceptionHandler.isCalled());

		testBean.failWithVoid();
		exceptionHandler.await(3000);
		Method m = ReflectionUtils.findMethod(TestBean.class, "failWithVoid");
		exceptionHandler.assertCalledWith(m, UnsupportedOperationException.class);
		context.close();
	}
