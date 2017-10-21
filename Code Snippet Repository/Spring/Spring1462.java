	@Test
	public void testPropertyPlaceholderConfigurerWithCircularReference() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("age", "${age}")
				.addPropertyValue("name", "name${var}")
				.getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.setProperty("age", "99");
		props.setProperty("var", "${m}");
		props.setProperty("m", "${var}");
		ppc.setProperties(props);

		try {
			ppc.postProcessBeanFactory(factory);
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			// expected
		}
	}
