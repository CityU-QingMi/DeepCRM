	@Test
	public void SPR12035() {
		ExpressionParser parser = new SpelExpressionParser();

		Expression expression1 = parser.parseExpression("list.?[ value>2 ].size()!=0");
		assertTrue(expression1.getValue(new BeanClass(new ListOf(1.1), new ListOf(2.2)),
				Boolean.class));

		Expression expression2 = parser.parseExpression("list.?[ T(java.lang.Math).abs(value) > 2 ].size()!=0");
		assertTrue(expression2.getValue(new BeanClass(new ListOf(1.1), new ListOf(-2.2)),
				Boolean.class));
	}
