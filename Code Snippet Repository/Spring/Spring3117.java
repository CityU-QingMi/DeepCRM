	@Test
	public void unavailableReturnValue() throws Exception {
		EvaluationContext context = createEvaluationContext(CacheOperationExpressionEvaluator.RESULT_UNAVAILABLE);
		try {
			new SpelExpressionParser().parseExpression("#result").getValue(context);
			fail("Should have failed to parse expression, result not available");
		}
		catch (VariableNotAvailableException e) {
			assertEquals("wrong variable name", "result", e.getName());
		}
	}
