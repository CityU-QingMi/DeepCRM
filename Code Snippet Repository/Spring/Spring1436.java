	@Test
	public void testPropertyPathFactoryBeanWithPrototypeResult() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONTEXT);
		assertNull(xbf.getType("tb.spouse"));
		assertEquals(TestBean.class, xbf.getType("propertyPath3"));
		Object result1 = xbf.getBean("tb.spouse");
		Object result2 = xbf.getBean("propertyPath3");
		Object result3 = xbf.getBean("propertyPath3");
		assertTrue(result1 instanceof TestBean);
		assertTrue(result2 instanceof TestBean);
		assertTrue(result3 instanceof TestBean);
		assertEquals(11, ((TestBean) result1).getAge());
		assertEquals(11, ((TestBean) result2).getAge());
		assertEquals(11, ((TestBean) result3).getAge());
		assertTrue(result1 != result2);
		assertTrue(result1 != result3);
		assertTrue(result2 != result3);
	}
