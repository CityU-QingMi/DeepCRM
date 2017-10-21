	@Test
	public void testPropertyPlaceholderConfigurerWithDefaultProperties() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("touchy", "${test}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("test", "mytest");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("mytest", tb.getTouchy());
	}
