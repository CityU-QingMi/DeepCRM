	@Test
	public void testPropertyOverrideConfigurerWithNestedMapPropertyAndDotInMapKey() {
		BeanDefinition def = BeanDefinitionBuilder.genericBeanDefinition(IndexedTestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("tb", def);

		PropertyOverrideConfigurer poc;
		poc = new PropertyOverrideConfigurer();
		Properties props = new Properties();
		props.setProperty("tb.map[key1]", "99");
		props.setProperty("tb.map[key2.ext]", "test");
		poc.setProperties(props);
		poc.postProcessBeanFactory(factory);

		IndexedTestBean tb = (IndexedTestBean) factory.getBean("tb");
		assertEquals("99", tb.getMap().get("key1"));
		assertEquals("test", tb.getMap().get("key2.ext"));
	}
