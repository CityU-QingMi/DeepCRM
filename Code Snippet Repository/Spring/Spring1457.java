	@Test
	public void testPropertyPlaceholderConfigurerWithEmptyStringInPlaceholderAsNull() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${ref}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setNullValue("");
		Properties props = new Properties();
		props.put("ref", "");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertNull(tb.getName());
	}
