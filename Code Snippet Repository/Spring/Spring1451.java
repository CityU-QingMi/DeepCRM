	@Test
	public void testPropertyPlaceholderConfigurerWithSystemPropertyNotUsed() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("country", "${os.name}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.setProperty("os.name", "myos");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("myos", tb.getCountry());
	}
