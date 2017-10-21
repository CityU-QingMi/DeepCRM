	@Test
	public void testSunnyDayPathLogsCorrectlyWithPrettyMuchAllPlaceholdersMatching() throws Throwable {

		MethodInvocation methodInvocation = mock(MethodInvocation.class);

		given(methodInvocation.getMethod()).willReturn(String.class.getMethod("toString", new Class[0]));
		given(methodInvocation.getThis()).willReturn(this);
		given(methodInvocation.getArguments()).willReturn(new Object[]{"$ One \\$", new Long(2)});
		given(methodInvocation.proceed()).willReturn("Hello!");

		Log log = mock(Log.class);
		given(log.isTraceEnabled()).willReturn(true);

		CustomizableTraceInterceptor interceptor = new StubCustomizableTraceInterceptor(log);
		interceptor.setEnterMessage(new StringBuffer()
			.append("Entering the '").append(CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME)
			.append("' method of the [").append(CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME)
			.append("] class with the following args (").append(CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENTS)
			.append(") and arg types (").append(CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENT_TYPES)
			.append(").").toString());
		interceptor.setExitMessage(new StringBuffer()
			.append("Exiting the '").append(CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME)
			.append("' method of the [").append(CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_SHORT_NAME)
			.append("] class with the following args (").append(CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENTS)
			.append(") and arg types (").append(CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENT_TYPES)
			.append("), returning '").append(CustomizableTraceInterceptor.PLACEHOLDER_RETURN_VALUE)
			.append("' and taking '").append(CustomizableTraceInterceptor.PLACEHOLDER_INVOCATION_TIME)
			.append("' this long.").toString());
		interceptor.invoke(methodInvocation);

		verify(log, times(2)).trace(anyString());
	}
