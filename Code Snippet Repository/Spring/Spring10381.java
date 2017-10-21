	@Test
	@SuppressWarnings("")
	public void withConfigurationClassWithPlainFactoryBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ConfigWithPlainFactoryBean.class);
		context.refresh();
		MyBean myBean = context.getBean("myBean", MyBean.class);
		assertSame(context.getBean("myService"), myBean.myService);
		myBean.myService.handle();
		myBean.myService.handleAsync();
	}
