	@Test
	public void configurationClassesWithValidOverridingForProgrammaticCall() {
		beanFactory.registerBeanDefinition("config1", new RootBeanDefinition(OverridingAgainSingletonBeanConfig.class));
		beanFactory.registerBeanDefinition("config2", new RootBeanDefinition(OverridingSingletonBeanConfig.class));
		beanFactory.registerBeanDefinition("config3", new RootBeanDefinition(SingletonBeanConfig.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);

		Foo foo = beanFactory.getBean(Foo.class);
		assertTrue(foo instanceof ExtendedAgainFoo);
		Bar bar = beanFactory.getBean(Bar.class);
		assertSame(foo, bar.foo);
	}
