	@Test
	void invalidExpressionEvaluationType() {
		String methodName = "nonBooleanOrStringExpression";
		IllegalStateException exception = assertThrows(IllegalStateException.class,
			() -> condition.evaluateExecutionCondition(buildExtensionContext(methodName)));

		Method method = ReflectionUtils.findMethod(getClass(), methodName);

		assertThat(exception.getMessage(),
			is(equalTo("@DisabledIf(\"#{6 * 7}\") on " + method + " must evaluate to a String or a Boolean, not java.lang.Integer")));
	}
