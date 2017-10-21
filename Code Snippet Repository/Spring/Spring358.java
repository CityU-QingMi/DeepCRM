	@Test
	public void testNotInvoked() throws Throwable {
		MyThrowsHandler th = new MyThrowsHandler();
		ThrowsAdviceInterceptor ti = new ThrowsAdviceInterceptor(th);
		Object ret = new Object();
		MethodInvocation mi = mock(MethodInvocation.class);
		given(mi.proceed()).willReturn(ret);
		assertEquals(ret, ti.invoke(mi));
		assertEquals(0, th.getCalls());
	}
