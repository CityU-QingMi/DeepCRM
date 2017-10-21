	@Test
	public void testConstructorInvocation06() throws Exception {
		// repeated evaluation to drive use of cached executor
		SpelExpression expr = (SpelExpression) parser.parseExpression("new String('wibble')");
		String newString = expr.getValue(String.class);
		assertEquals("wibble", newString);
		newString = expr.getValue(String.class);
		assertEquals("wibble", newString);

		// not writable
		assertFalse(expr.isWritable(new StandardEvaluationContext()));

		// ast
		assertEquals("new String('wibble')", expr.toStringAST());
	}
