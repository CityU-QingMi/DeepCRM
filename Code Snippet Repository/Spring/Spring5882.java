	@Test
	public void testSetValue() {
		try {
			LiteralExpression lEx = new LiteralExpression("somevalue");
			lEx.setValue(new StandardEvaluationContext(), "flibble");
			fail("Should have got an exception that the value cannot be set");
		}
		catch (EvaluationException ee) {
			// success, not allowed - whilst here, check the expression value in the exception
			assertEquals(ee.getExpressionString(), "somevalue");
		}
		try {
			LiteralExpression lEx = new LiteralExpression("somevalue");
			lEx.setValue(new Rooty(), "flibble");
			fail("Should have got an exception that the value cannot be set");
		}
		catch (EvaluationException ee) {
			// success, not allowed - whilst here, check the expression value in the exception
			assertEquals(ee.getExpressionString(), "somevalue");
		}
		try {
			LiteralExpression lEx = new LiteralExpression("somevalue");
			lEx.setValue(new StandardEvaluationContext(), new Rooty(), "flibble");
			fail("Should have got an exception that the value cannot be set");
		}
		catch (EvaluationException ee) {
			// success, not allowed - whilst here, check the expression value in the exception
			assertEquals(ee.getExpressionString(), "somevalue");
		}
	}
