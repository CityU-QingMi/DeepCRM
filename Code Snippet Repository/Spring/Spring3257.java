	@Test
	public void configurationClassesWithInvalidOverridingForProgrammaticCall() {
		beanFactory.registerBeanDefinition("config1", new RootBeanDefinition(InvalidOverridingSingletonBeanConfig.class));
		beanFactory.registerBeanDefinition("config2", new RootBeanDefinition(OverridingSingletonBeanConfig.class));
		beanFactory.registerBeanDefinition("config3", new RootBeanDefinition(SingletonBeanConfig.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);

		try {
			beanFactory.getBean(Bar.class);
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMessage().contains("OverridingSingletonBeanConfig.foo"));
			assertTrue(ex.getMessage().contains(ExtendedFoo.class.getName()));
			assertTrue(ex.getMessage().contains(Foo.class.getName()));
			assertTrue(ex.getMessage().contains("InvalidOverridingSingletonBeanConfig"));
		}
	}
