	@Test
	public void variableReference_userDefined() throws Exception {
		EvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("target", "abc");
		expression = parser.parseExpression("#target");
		assertEquals("abc", expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals("abc", expression.getValue(ctx));
		ctx.setVariable("target", "123");
		assertEquals("123", expression.getValue(ctx));
		ctx.setVariable("target", 42);
		try {
			assertEquals(42, expression.getValue(ctx));
			fail();
		}
		catch (SpelEvaluationException see) {
			assertTrue(see.getCause() instanceof ClassCastException);
		}

		ctx.setVariable("target", "abc");
		expression = parser.parseExpression("#target.charAt(0)");
		assertEquals('a', expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals('a', expression.getValue(ctx));
		ctx.setVariable("target", "1");
		assertEquals('1', expression.getValue(ctx));
		ctx.setVariable("target", 42);
		try {
			assertEquals('4', expression.getValue(ctx));
			fail();
		}
		catch (SpelEvaluationException see) {
			assertTrue(see.getCause() instanceof ClassCastException);
		}
	}
