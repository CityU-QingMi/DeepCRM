	@Test
	void unsupportedStringEvaluationValue() {
		String methodName = "stringExpressionThatIsNeitherTrueNorFalse";
		IllegalStateException exception = assertThrows(IllegalStateException.class,
			() -> condition.evaluateExecutionCondition(buildExtensionContext(methodName)));

		Method method = ReflectionUtils.findMethod(getClass(), methodName);

		assertThat(exception.getMessage(),
			is(equalTo("@DisabledIf(\"#{'enigma'}\") on " + method + " must evaluate to \"true\" or \"false\", not \"enigma\"")));
	}
