	@Test
	public void testPropertyOverrideConfigurerWithNestedPropertyAndDotInBeanName() {
		BeanDefinition def = BeanDefinitionBuilder.genericBeanDefinition(IndexedTestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("my.tb", def);

		PropertyOverrideConfigurer poc;
		poc = new PropertyOverrideConfigurer();
		Properties props = new Properties();
		props.setProperty("my.tb_array[0].age", "99");
		props.setProperty("my.tb_list[1].name", "test");
		poc.setProperties(props);
		poc.setBeanNameSeparator("_");
		poc.postProcessBeanFactory(factory);

		IndexedTestBean tb = (IndexedTestBean) factory.getBean("my.tb");
		assertEquals(99, tb.getArray()[0].getAge());
		assertEquals("test", ((TestBean) tb.getList().get(1)).getName());
	}
