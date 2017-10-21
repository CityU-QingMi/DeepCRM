	@Test
	public void opGt() throws Exception {
		expression = parse("3.0d > 4.0d");
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());
		expression = parse("3446.0d > 1123.0d");
		assertCanCompile(expression);
		assertTrue((Boolean) expression.getValue());

		expression = parse("3 > 1");
		assertCanCompile(expression);
		assertTrue((Boolean) expression.getValue());
		expression = parse("2 > 4");
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());

		expression = parse("3.0f > 1.0f");
		assertCanCompile(expression);
		assertTrue((Boolean) expression.getValue());
		expression = parse("1.0f > 5.0f");
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());

		expression = parse("30L > 30L");
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());
		expression = parse("15L > 20L");
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());

		// Differing types of number, not yet supported
		expression = parse("1 > 3.0d");
		assertCantCompile(expression);

		expression = parse("T(Integer).valueOf(3) > 4");
		assertFalse((Boolean) expression.getValue());
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());

		expression = parse("T(Integer).valueOf(3) > T(Integer).valueOf(3)");
		assertFalse((Boolean) expression.getValue());
		assertCanCompile(expression);
		assertFalse((Boolean) expression.getValue());

		expression = parse("5 > T(Integer).valueOf(3)");
		assertTrue((Boolean) expression.getValue());
		assertCanCompile(expression);
		assertTrue((Boolean) expression.getValue());
	}
