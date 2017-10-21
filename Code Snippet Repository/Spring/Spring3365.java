	@Test
	public void configurationWithPostProcessor() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithPostProcessor.class);
		RootBeanDefinition placeholderConfigurer = new RootBeanDefinition(PropertyPlaceholderConfigurer.class);
		placeholderConfigurer.getPropertyValues().add("properties", "myProp=myValue");
		ctx.registerBeanDefinition("placeholderConfigurer", placeholderConfigurer);
		ctx.refresh();

		TestBean foo = ctx.getBean("foo", TestBean.class);
		ITestBean bar = ctx.getBean("bar", ITestBean.class);
		ITestBean baz = ctx.getBean("baz", ITestBean.class);

		assertEquals("foo-processed-myValue", foo.getName());
		assertEquals("bar-processed-myValue", bar.getName());
		assertEquals("baz-processed-myValue", baz.getName());

		SpousyTestBean listener = ctx.getBean("listenerTestBean", SpousyTestBean.class);
		assertTrue(listener.refreshed);
		ctx.close();
	}
