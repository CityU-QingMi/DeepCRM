	@Test
	public void testGetObjectTypeWithDirectTarget() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(TARGETSOURCE_CONTEXT, CLASS));

		// We have a counting before advice here
		CountingBeforeAdvice cba = (CountingBeforeAdvice) bf.getBean("countingBeforeAdvice");
		assertEquals(0, cba.getCalls());

		ITestBean tb = (ITestBean) bf.getBean("directTarget");
		assertTrue(tb.getName().equals("Adam"));
		assertEquals(1, cba.getCalls());

		ProxyFactoryBean pfb = (ProxyFactoryBean) bf.getBean("&directTarget");
		assertTrue("Has correct object type", TestBean.class.isAssignableFrom(pfb.getObjectType()));
	}
