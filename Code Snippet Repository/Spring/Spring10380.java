	@Test
	@SuppressWarnings("")
	public void testNonLoadedConfigClass() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBeanDefinition("config", new RootBeanDefinition(InvokerAutowiringConfig.class.getName()));
		context.refresh();
		MyBean myBean = context.getBean("myBean", MyBean.class);
		assertSame(context.getBean("myService"), myBean.myService);
		myBean.myService.handle();
		myBean.myService.handleAsync();
	}
