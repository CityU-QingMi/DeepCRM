	@Test
	public void testPropertyOverrideConfigurerWithNestedProperty() {
		BeanDefinition def = BeanDefinitionBuilder.genericBeanDefinition(IndexedTestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("tb", def);

		PropertyOverrideConfigurer poc;
		poc = new PropertyOverrideConfigurer();
		Properties props = new Properties();
		props.setProperty("tb.array[0].age", "99");
		props.setProperty("tb.list[1].name", "test");
		poc.setProperties(props);
		poc.postProcessBeanFactory(factory);

		IndexedTestBean tb = (IndexedTestBean) factory.getBean("tb");
		assertEquals(99, tb.getArray()[0].getAge());
		assertEquals("test", ((TestBean) tb.getList().get(1)).getName());
	}
