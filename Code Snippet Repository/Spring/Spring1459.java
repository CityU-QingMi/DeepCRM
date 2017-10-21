	@Test
	public void testPropertyPlaceholderConfigurerWithNestedPlaceholderInKey() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${my${key}key}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("key", "new");
		props.put("mynewkey", "myname");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("myname", tb.getName());
	}
