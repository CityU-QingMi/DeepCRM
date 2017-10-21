	@Test
	public void operatorInstanceOf_SPR14250() throws Exception {
		// primitive left operand - should get boxed, return true
		expression = parse("3 instanceof T(Integer)");
		assertEquals(true, expression.getValue());
		assertCanCompile(expression);
		assertEquals(true, expression.getValue());

		// primitive left operand - should get boxed, return false
		expression = parse("3 instanceof T(String)");
		assertEquals(false, expression.getValue());
		assertCanCompile(expression);
		assertEquals(false, expression.getValue());

		// double slot left operand - should get boxed, return false
		expression = parse("3.0d instanceof T(Integer)");
		assertEquals(false, expression.getValue());
		assertCanCompile(expression);
		assertEquals(false, expression.getValue());

		// double slot left operand - should get boxed, return true
		expression = parse("3.0d instanceof T(Double)");
		assertEquals(true, expression.getValue());
		assertCanCompile(expression);
		assertEquals(true, expression.getValue());

		// Only when the right hand operand is a direct type reference
		// will it be compilable.
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("foo", String.class);
		expression = parse("3 instanceof #foo");
		assertEquals(false, expression.getValue(ctx));
		assertCantCompile(expression);

		// use of primitive as type for instanceof check - compilable
		// but always false
		expression = parse("3 instanceof T(int)");
		assertEquals(false, expression.getValue());
		assertCanCompile(expression);
		assertEquals(false, expression.getValue());

		expression = parse("3 instanceof T(long)");
		assertEquals(false, expression.getValue());
		assertCanCompile(expression);
		assertEquals(false, expression.getValue());
	}
