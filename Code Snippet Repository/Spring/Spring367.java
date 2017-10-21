	@Test
	public void testExceptionPathLogsCorrectly() throws Throwable {

		MethodInvocation methodInvocation = mock(MethodInvocation.class);

		IllegalArgumentException exception = new IllegalArgumentException();
		given(methodInvocation.getMethod()).willReturn(String.class.getMethod("toString", new Class[]{}));
		given(methodInvocation.getThis()).willReturn(this);
		given(methodInvocation.proceed()).willThrow(exception);

		Log log = mock(Log.class);
		given(log.isTraceEnabled()).willReturn(true);

		CustomizableTraceInterceptor interceptor = new StubCustomizableTraceInterceptor(log);
		try {
			interceptor.invoke(methodInvocation);
			fail("Must have propagated the IllegalArgumentException.");
		}
		catch (IllegalArgumentException expected) {
		}

		verify(log).trace(anyString());
		verify(log).trace(anyString(), eq(exception));
	}
