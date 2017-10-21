	@Test
	public void testPropertyPlaceholderConfigurerWithUnresolvableSystemProperty() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("touchy", "${user.dir}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_NEVER);

		try {
			ppc.postProcessBeanFactory(factory);
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			// expected
			assertTrue(ex.getMessage().contains("user.dir"));
		}
	}
