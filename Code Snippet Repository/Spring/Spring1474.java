	@Test
	public void testPropertyOverrideConfigurerWithPropertiesXmlFile() {
		BeanDefinition def = BeanDefinitionBuilder.genericBeanDefinition(IndexedTestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("tb", def);

		PropertyOverrideConfigurer poc = new PropertyOverrideConfigurer();
		poc.setLocation(TEST_PROPS_XML);
		poc.postProcessBeanFactory(factory);

		IndexedTestBean tb = (IndexedTestBean) factory.getBean("tb");
		assertEquals(99, tb.getArray()[0].getAge());
		assertEquals("test", ((TestBean) tb.getList().get(1)).getName());
	}
