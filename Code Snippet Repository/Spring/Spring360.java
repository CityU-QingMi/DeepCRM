	@Test
	public void testCorrectHandlerUsed() throws Throwable {
		MyThrowsHandler th = new MyThrowsHandler();
		ThrowsAdviceInterceptor ti = new ThrowsAdviceInterceptor(th);
		FileNotFoundException ex = new FileNotFoundException();
		MethodInvocation mi = mock(MethodInvocation.class);
		given(mi.getMethod()).willReturn(Object.class.getMethod("hashCode"));
		given(mi.getThis()).willReturn(new Object());
		given(mi.proceed()).willThrow(ex);
		try {
			ti.invoke(mi);
			fail();
		}
		catch (Exception caught) {
			assertEquals(ex, caught);
		}
		assertEquals(1, th.getCalls());
		assertEquals(1, th.getCalls("ioException"));
	}
