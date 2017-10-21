	@Test
	public void testCustomPrototypeTargetSource() throws Exception {
		CountingTestBean.count = 0;
		BeanFactory bf = new ClassPathXmlApplicationContext(CUSTOM_TARGETSOURCE_CONTEXT, CLASS);
		ITestBean test = (ITestBean) bf.getBean("prototypeTest");
		assertTrue(AopUtils.isAopProxy(test));
		Advised advised = (Advised) test;
		assertTrue(advised.getTargetSource() instanceof PrototypeTargetSource);
		assertEquals("Rod", test.getName());
		// Check that references survived prototype creation
		assertEquals("Kerry", test.getSpouse().getName());
		assertEquals("Only 2 CountingTestBeans instantiated", 2, CountingTestBean.count);
		CountingTestBean.count = 0;
	}
