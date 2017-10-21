	@Test
	public void configurationClassesMayNotBeFinal() {
		@Configuration
		final class Config { }

		BeanDefinition configBeanDef = rootBeanDefinition(Config.class).getBeanDefinition();
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("config", configBeanDef);

		try {
			ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
			pp.postProcessBeanFactory(beanFactory);
			fail("expected exception");
		}
		catch (BeanDefinitionParsingException ex) {
			assertTrue(ex.getMessage(), ex.getMessage().contains("Remove the final modifier"));
		}
	}
