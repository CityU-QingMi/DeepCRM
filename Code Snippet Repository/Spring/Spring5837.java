	@Test
	public void testTypeReferencesAndQualifiedIdentifierCaching() throws Exception {
		SpelExpression expr = (SpelExpression) parser.parseExpression("T(java.lang.String)");
		assertFalse(expr.isWritable(new StandardEvaluationContext()));
		assertEquals("T(java.lang.String)", expr.toStringAST());
		assertEquals(String.class, expr.getValue(Class.class));
		// use cached QualifiedIdentifier:
		assertEquals("T(java.lang.String)", expr.toStringAST());
		assertEquals(String.class, expr.getValue(Class.class));
	}
