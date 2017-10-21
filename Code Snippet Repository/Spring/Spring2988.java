	@Test
	public void testInnerBeansWithoutDestroy() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		reader.loadBeanDefinitions(REFTYPES_CONTEXT);

		// Let's create the outer bean named "innerBean",
		// to check whether it doesn't create any conflicts
		// with the actual inner beans named "innerBean".
		xbf.getBean("innerBean");

		TestBean hasInnerBeans = (TestBean) xbf.getBean("hasInnerBeansWithoutDestroy");
		assertEquals(5, hasInnerBeans.getAge());
		TestBean inner1 = (TestBean) hasInnerBeans.getSpouse();
		assertNotNull(inner1);
		assertTrue(inner1.getBeanName().startsWith("innerBean"));
		assertEquals("inner1", inner1.getName());
		assertEquals(6, inner1.getAge());

		assertNotNull(hasInnerBeans.getFriends());
		Object[] friends = hasInnerBeans.getFriends().toArray();
		assertEquals(3, friends.length);
		DerivedTestBean inner2 = (DerivedTestBean) friends[0];
		assertEquals("inner2", inner2.getName());
		assertTrue(inner2.getBeanName().startsWith(DerivedTestBean.class.getName()));
		assertNotNull(inner2);
		assertEquals(7, inner2.getAge());
		TestBean innerFactory = (TestBean) friends[1];
		assertEquals(DummyFactory.SINGLETON_NAME, innerFactory.getName());
		TestBean inner5 = (TestBean) friends[2];
		assertTrue(inner5.getBeanName().startsWith("innerBean"));
	}
