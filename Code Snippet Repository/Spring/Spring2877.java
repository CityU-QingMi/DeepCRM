	@Test
	public void testLazyInitTargetSource() throws Exception {
		CountingTestBean.count = 0;
		BeanFactory bf = new ClassPathXmlApplicationContext(CUSTOM_TARGETSOURCE_CONTEXT, CLASS);
		ITestBean test = (ITestBean) bf.getBean("lazyInitTest");
		assertTrue(AopUtils.isAopProxy(test));
		Advised advised = (Advised) test;
		assertTrue(advised.getTargetSource() instanceof LazyInitTargetSource);
		assertEquals("No CountingTestBean instantiated yet", 0, CountingTestBean.count);
		assertEquals("Rod", test.getName());
		assertEquals("Kerry", test.getSpouse().getName());
		assertEquals("Only 1 CountingTestBean instantiated", 1, CountingTestBean.count);
		CountingTestBean.count = 0;
	}
