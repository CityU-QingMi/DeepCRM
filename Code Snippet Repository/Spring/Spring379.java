	@Test
	public void testSunnyDayPathLogsCorrectly() throws Throwable {
		MethodInvocation mi = mock(MethodInvocation.class);
		given(mi.getMethod()).willReturn(String.class.getMethod("toString", new Class[]{}));
		given(mi.getThis()).willReturn(this);

		Log log = mock(Log.class);

		SimpleTraceInterceptor interceptor = new SimpleTraceInterceptor(true);
		interceptor.invokeUnderTrace(mi, log);

		verify(log, times(2)).trace(anyString());
	}
