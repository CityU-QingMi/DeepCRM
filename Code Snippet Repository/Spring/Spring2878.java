	@Test
	public void testQuickTargetSourceCreator() throws Exception {
		ClassPathXmlApplicationContext bf =
				new ClassPathXmlApplicationContext(QUICK_TARGETSOURCE_CONTEXT, CLASS);
		ITestBean test = (ITestBean) bf.getBean("test");
		assertFalse(AopUtils.isAopProxy(test));
		assertEquals("Rod", test.getName());
		// Check that references survived pooling
		assertEquals("Kerry", test.getSpouse().getName());

		// Now test the pooled one
		test = (ITestBean) bf.getBean(":test");
		assertTrue(AopUtils.isAopProxy(test));
		Advised advised = (Advised) test;
		assertTrue(advised.getTargetSource() instanceof CommonsPool2TargetSource);
		assertEquals("Rod", test.getName());
		// Check that references survived pooling
		assertEquals("Kerry", test.getSpouse().getName());

		// Now test the ThreadLocal one
		test = (ITestBean) bf.getBean("%test");
		assertTrue(AopUtils.isAopProxy(test));
		advised = (Advised) test;
		assertTrue(advised.getTargetSource() instanceof ThreadLocalTargetSource);
		assertEquals("Rod", test.getName());
		// Check that references survived pooling
		assertEquals("Kerry", test.getSpouse().getName());

		// Now test the Prototype TargetSource
		test = (ITestBean) bf.getBean("!test");
		assertTrue(AopUtils.isAopProxy(test));
		advised = (Advised) test;
		assertTrue(advised.getTargetSource() instanceof PrototypeTargetSource);
		assertEquals("Rod", test.getName());
		// Check that references survived pooling
		assertEquals("Kerry", test.getSpouse().getName());


		ITestBean test2 = (ITestBean) bf.getBean("!test");
		assertFalse("Prototypes cannot be the same object", test == test2);
		assertEquals("Rod", test2.getName());
		assertEquals("Kerry", test2.getSpouse().getName());
		bf.close();
	}
