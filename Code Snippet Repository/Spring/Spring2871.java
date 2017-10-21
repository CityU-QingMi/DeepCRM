	@Test
	public void testTargetAsInnerBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(INNER_BEAN_TARGET_CONTEXT, CLASS));
		ITestBean itb = (ITestBean) bf.getBean("testBean");
		assertEquals("innerBeanTarget", itb.getName());
		assertEquals("Only have proxy and interceptor: no target", 3, bf.getBeanDefinitionCount());
		DependsOnITestBean doit = (DependsOnITestBean) bf.getBean("autowireCheck");
		assertSame(itb, doit.tb);
	}
