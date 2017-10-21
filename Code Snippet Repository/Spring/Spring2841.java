	@Test
	public void testProxyCanBeClassNotInterface() throws Exception {
		TestBean raw = new TestBean();
		raw.setAge(32);
		mockTargetSource.setTarget(raw);
		AdvisedSupport pc = new AdvisedSupport();
		pc.setTargetSource(mockTargetSource);
		AopProxy aop = new CglibAopProxy(pc);

		Object proxy = aop.getProxy();
		assertTrue(AopUtils.isCglibProxy(proxy));
		assertTrue(proxy instanceof ITestBean);
		assertTrue(proxy instanceof TestBean);

		TestBean tb = (TestBean) proxy;
		assertEquals(32, tb.getAge());
	}
