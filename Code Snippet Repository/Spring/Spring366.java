	@Test
	public void testSunnyDayPathLogsCorrectly() throws Throwable {

		MethodInvocation methodInvocation = mock(MethodInvocation.class);
		given(methodInvocation.getMethod()).willReturn(String.class.getMethod("toString", new Class[]{}));
		given(methodInvocation.getThis()).willReturn(this);

		Log log = mock(Log.class);
		given(log.isTraceEnabled()).willReturn(true);

		CustomizableTraceInterceptor interceptor = new StubCustomizableTraceInterceptor(log);
		interceptor.invoke(methodInvocation);

		verify(log, times(2)).trace(anyString());
	}
