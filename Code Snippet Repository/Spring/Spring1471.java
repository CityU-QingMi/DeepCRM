	@Test
	public void testPropertyOverrideConfigurerWithHeldProperties() {
		BeanDefinition def = BeanDefinitionBuilder.genericBeanDefinition(PropertiesHolder.class).getBeanDefinition();
		factory.registerBeanDefinition("tb", def);

		PropertyOverrideConfigurer poc;
		poc = new PropertyOverrideConfigurer();
		Properties props = new Properties();
		props.setProperty("tb.heldProperties[mail.smtp.auth]", "true");
		poc.setProperties(props);
		poc.postProcessBeanFactory(factory);

		PropertiesHolder tb = (PropertiesHolder) factory.getBean("tb");
		assertEquals("true", tb.getHeldProperties().getProperty("mail.smtp.auth"));
	}
