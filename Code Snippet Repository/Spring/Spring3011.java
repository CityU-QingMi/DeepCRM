	@Test
	public void testConstructorArgWithDoubleSimpleTypeMatch() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONSTRUCTOR_ARG_CONTEXT);

		SingleSimpleTypeConstructorBean bean = (SingleSimpleTypeConstructorBean) xbf.getBean("beanWithBooleanAndString");
		assertTrue(bean.isSecondBoolean());
		assertEquals("A String", bean.getTestString());

		SingleSimpleTypeConstructorBean bean2 = (SingleSimpleTypeConstructorBean) xbf.getBean("beanWithBooleanAndString2");
		assertTrue(bean2.isSecondBoolean());
		assertEquals("A String", bean2.getTestString());
	}
