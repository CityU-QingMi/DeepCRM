	@Test
	public void taskExecutorByBeanName() {
		StaticApplicationContext context = new StaticApplicationContext();

		BeanDefinition processorDefinition = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
		context.registerBeanDefinition("postProcessor", processorDefinition);

		BeanDefinition executorDefinition = new RootBeanDefinition(ThreadPoolTaskExecutor.class);
		executorDefinition.getPropertyValues().add("threadNamePrefix", "testExecutor");
		context.registerBeanDefinition("myExecutor", executorDefinition);

		BeanDefinition executorDefinition2 = new RootBeanDefinition(ThreadPoolTaskExecutor.class);
		executorDefinition2.getPropertyValues().add("threadNamePrefix", "testExecutor2");
		context.registerBeanDefinition("taskExecutor", executorDefinition2);

		BeanDefinition targetDefinition =
				new RootBeanDefinition(AsyncAnnotationBeanPostProcessorTests.TestBean.class);
		context.registerBeanDefinition("target", targetDefinition);

		context.refresh();

		ITestBean testBean = context.getBean("target", ITestBean.class);
		testBean.test();
		testBean.await(3000);
		Thread asyncThread = testBean.getThread();
		assertTrue(asyncThread.getName().startsWith("testExecutor2"));
		context.close();
	}
