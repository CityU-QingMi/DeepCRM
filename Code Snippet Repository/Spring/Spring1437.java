	@Test
	public void testPropertyPathFactoryBeanAsInnerBean() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONTEXT);
		TestBean spouse = (TestBean) xbf.getBean("otb.spouse");
		TestBean tbWithInner = (TestBean) xbf.getBean("tbWithInner");
		assertSame(spouse, tbWithInner.getSpouse());
		assertTrue(!tbWithInner.getFriends().isEmpty());
		assertSame(spouse, tbWithInner.getFriends().iterator().next());
	}
