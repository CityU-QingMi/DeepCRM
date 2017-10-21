	@Test
	public void functionReference() throws Exception {
		EvaluationContext ctx = new StandardEvaluationContext();
		Method m = getClass().getDeclaredMethod("concat", String.class, String.class);
		ctx.setVariable("concat",m);

		expression = parser.parseExpression("#concat('a','b')");
		assertEquals("ab", expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals("ab", expression.getValue(ctx));

		expression = parser.parseExpression("#concat(#concat('a','b'),'c').charAt(1)");
		assertEquals('b', expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals('b', expression.getValue(ctx));

		expression = parser.parseExpression("#concat(#a,#b)");
		ctx.setVariable("a", "foo");
		ctx.setVariable("b", "bar");
		assertEquals("foobar", expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals("foobar", expression.getValue(ctx));
		ctx.setVariable("b", "boo");
		assertEquals("fooboo", expression.getValue(ctx));

		m = Math.class.getDeclaredMethod("pow", Double.TYPE, Double.TYPE);
		ctx.setVariable("kapow",m);
		expression = parser.parseExpression("#kapow(2.0d,2.0d)");
		assertEquals("4.0", expression.getValue(ctx).toString());
		assertCanCompile(expression);
		assertEquals("4.0", expression.getValue(ctx).toString());
	}
