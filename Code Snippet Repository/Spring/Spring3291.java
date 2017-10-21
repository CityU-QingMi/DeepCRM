	@Test
	public void postProcessorFailsOnImplicitOverrideIfOverridingIsNotAllowed() {
		RootBeanDefinition rbd = new RootBeanDefinition(TestBean.class);
		rbd.setResource(new DescriptiveResource("XML or something"));
		beanFactory.registerBeanDefinition("bar", rbd);
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(SingletonBeanConfig.class));
		beanFactory.setAllowBeanDefinitionOverriding(false);
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		try {
			pp.postProcessBeanFactory(beanFactory);
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			assertTrue(ex.getMessage().contains("bar"));
			assertTrue(ex.getMessage().contains("SingletonBeanConfig"));
			assertTrue(ex.getMessage().contains(TestBean.class.getName()));
		}
	}
