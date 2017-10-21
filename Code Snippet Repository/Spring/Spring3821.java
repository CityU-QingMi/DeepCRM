	@Test
	public void threadNamePrefix() {
		BeanDefinition processorDefinition = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("testExecutor");
		executor.afterPropertiesSet();
		processorDefinition.getPropertyValues().add("executor", executor);
		ConfigurableApplicationContext context = initContext(processorDefinition);
		ITestBean testBean = context.getBean("target", ITestBean.class);
		testBean.test();
		testBean.await(3000);
		Thread asyncThread = testBean.getThread();
		assertTrue(asyncThread.getName().startsWith("testExecutor"));
		context.close();
	}
