	@Test
	public void nestedConfigurationClassesProcessedInCorrectOrder() {
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(ConfigWithOrderedNestedClasses.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);

		Foo foo = beanFactory.getBean(Foo.class);
		assertTrue(foo instanceof ExtendedFoo);
		Bar bar = beanFactory.getBean(Bar.class);
		assertSame(foo, bar.foo);
	}
