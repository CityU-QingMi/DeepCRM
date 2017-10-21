	@Test
	public void testPropertyPathFactoryBeanWithSingletonResult() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONTEXT);
		assertEquals(new Integer(12), xbf.getBean("propertyPath1"));
		assertEquals(new Integer(11), xbf.getBean("propertyPath2"));
		assertEquals(new Integer(10), xbf.getBean("tb.age"));
		assertEquals(ITestBean.class, xbf.getType("otb.spouse"));
		Object result1 = xbf.getBean("otb.spouse");
		Object result2 = xbf.getBean("otb.spouse");
		assertTrue(result1 instanceof TestBean);
		assertTrue(result1 == result2);
		assertEquals(99, ((TestBean) result1).getAge());
	}
