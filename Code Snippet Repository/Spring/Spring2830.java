	@Test
	public void testProtectedMethodInvocation() {
		ProtectedMethodTestBean bean = new ProtectedMethodTestBean();
		bean.value = "foo";
		mockTargetSource.setTarget(bean);

		AdvisedSupport as = new AdvisedSupport();
		as.setTargetSource(mockTargetSource);
		as.addAdvice(new NopInterceptor());
		AopProxy aop = new CglibAopProxy(as);

		ProtectedMethodTestBean proxy = (ProtectedMethodTestBean) aop.getProxy();
		assertTrue(AopUtils.isCglibProxy(proxy));
		assertEquals(proxy.getClass().getClassLoader(), bean.getClass().getClassLoader());
		assertEquals("foo", proxy.getString());
	}
