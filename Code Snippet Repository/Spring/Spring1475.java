	@Test
	public void testPropertyOverrideConfigurerWithConvertProperties() {
		BeanDefinition def = BeanDefinitionBuilder.genericBeanDefinition(IndexedTestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("tb", def);

		ConvertingOverrideConfigurer bfpp = new ConvertingOverrideConfigurer();
		Properties props = new Properties();
		props.setProperty("tb.array[0].name", "99");
		props.setProperty("tb.list[1].name", "test");
		bfpp.setProperties(props);
		bfpp.postProcessBeanFactory(factory);

		IndexedTestBean tb = (IndexedTestBean) factory.getBean("tb");
		assertEquals("X99", tb.getArray()[0].getName());
		assertEquals("Xtest", ((TestBean) tb.getList().get(1)).getName());
	}
