	@Test
	public void testAccessingOnNullObject() throws Exception {
		SpelExpression expr = (SpelExpression)parser.parseExpression("madeup");
		EvaluationContext context = new StandardEvaluationContext(null);
		try {
			expr.getValue(context);
			fail("Should have failed - default property resolver cannot resolve on null");
		}
		catch (Exception ex) {
			checkException(ex, SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL);
		}
		assertFalse(expr.isWritable(context));
		try {
			expr.setValue(context,"abc");
			fail("Should have failed - default property resolver cannot resolve on null");
		}
		catch (Exception ex) {
			checkException(ex, SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE_ON_NULL);
		}
	}
