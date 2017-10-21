	@Test
	public void testPropertyPlaceholderConfigurerWithSystemPropertyFallback() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("country", "${os.name}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals(System.getProperty("os.name"), tb.getCountry());
	}
