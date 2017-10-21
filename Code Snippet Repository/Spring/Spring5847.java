	@Test
	public void testOperators() throws Exception {
		ExpressionState state = getState();
		try {
			state.operate(Operation.ADD,1,2);
			fail("should have failed");
		}
		catch (EvaluationException ee) {
			SpelEvaluationException sEx = (SpelEvaluationException)ee;
			assertEquals(SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES,sEx.getMessageCode());
		}

		try {
			state.operate(Operation.ADD,null,null);
			fail("should have failed");
		}
		catch (EvaluationException ee) {
			SpelEvaluationException sEx = (SpelEvaluationException)ee;
			assertEquals(SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES,sEx.getMessageCode());
		}
	}
