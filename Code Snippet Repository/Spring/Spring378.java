	@Test
	public void testExceptionPathStillLogsPerformanceMetricsCorrectly() throws Throwable {
		MethodInvocation mi = mock(MethodInvocation.class);

		given(mi.getMethod()).willReturn(String.class.getMethod("toString", new Class[0]));
		given(mi.proceed()).willThrow(new IllegalArgumentException());
		Log log = mock(Log.class);

		PerformanceMonitorInterceptor interceptor = new PerformanceMonitorInterceptor(true);
		try {
			interceptor.invokeUnderTrace(mi, log);
			fail("Must have propagated the IllegalArgumentException.");
		}
		catch (IllegalArgumentException expected) {
		}

		verify(log).trace(anyString());
	}
