	private void assertSupportForComposedAnnotationWithExclude(RootBeanDefinition beanDefinition) {
		beanFactory.registerBeanDefinition("config", beanDefinition);
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.setEnvironment(new StandardEnvironment());
		pp.postProcessBeanFactory(beanFactory);
		try {
			beanFactory.getBean(SimpleComponent.class);
			fail("Should have thrown NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// expected
		}
	}
