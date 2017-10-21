	@Test
	public void invokedAsynchronously() {
		ConfigurableApplicationContext context = initContext(
				new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class));
		ITestBean testBean = context.getBean("target", ITestBean.class);
		testBean.test();
		Thread mainThread = Thread.currentThread();
		testBean.await(3000);
		Thread asyncThread = testBean.getThread();
		assertNotSame(mainThread, asyncThread);
		context.close();
	}
