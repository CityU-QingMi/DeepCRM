	@Test
	public void taskExecutorByBeanType() {
		StaticApplicationContext context = new StaticApplicationContext();

		BeanDefinition processorDefinition = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
		context.registerBeanDefinition("postProcessor", processorDefinition);

		BeanDefinition executorDefinition = new RootBeanDefinition(ThreadPoolTaskExecutor.class);
		executorDefinition.getPropertyValues().add("threadNamePrefix", "testExecutor");
		context.registerBeanDefinition("myExecutor", executorDefinition);

		BeanDefinition targetDefinition =
				new RootBeanDefinition(AsyncAnnotationBeanPostProcessorTests.TestBean.class);
		context.registerBeanDefinition("target", targetDefinition);

		context.refresh();

		ITestBean testBean = context.getBean("target", ITestBean.class);
		testBean.test();
		testBean.await(3000);
		Thread asyncThread = testBean.getThread();
		assertTrue(asyncThread.getName().startsWith("testExecutor"));
		context.close();
	}
