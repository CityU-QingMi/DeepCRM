	protected void setValue(String expression, Object value, Object expectedValue) {
		try {
			Expression e = parser.parseExpression(expression);
			if (e == null) {
				fail("Parser returned null for expression");
			}
			if (DEBUG) {
				SpelUtilities.printAbstractSyntaxTree(System.out, e);
			}
			StandardEvaluationContext lContext = TestScenarioCreator.getTestEvaluationContext();
			assertTrue("Expression is not writeable but should be", e.isWritable(lContext));
			e.setValue(lContext, value);
			Object a = expectedValue;
			Object b = e.getValue(lContext);
			if (!a.equals(b)) {
				fail("Not the same: ["+a+"] type="+a.getClass()+"  ["+b+"] type="+b.getClass());
//				assertEquals("Retrieved value was not equal to set value", expectedValue, e.getValue(lContext));
			}
		}
		catch (EvaluationException ee) {
			ee.printStackTrace();
			fail("Unexpected Exception: " + ee.getMessage());
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			fail("Unexpected Exception: " + pe.getMessage());
		}
	}
