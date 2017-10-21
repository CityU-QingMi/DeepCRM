	@Test
	public void testMapKeysThatAreAlsoSpELKeywords() {
		SpelExpressionParser parser = new SpelExpressionParser();
		SpelExpression expression = null;
		Object o = null;

		// expression = (SpelExpression) parser.parseExpression("foo['NEW']");
		// o = expression.getValue(new MapHolder());
		// assertEquals("VALUE",o);

		expression = (SpelExpression) parser.parseExpression("foo[T]");
		o = expression.getValue(new MapHolder());
		assertEquals("TV", o);

		expression = (SpelExpression) parser.parseExpression("foo[t]");
		o = expression.getValue(new MapHolder());
		assertEquals("tv", o);

		expression = (SpelExpression) parser.parseExpression("foo[NEW]");
		o = expression.getValue(new MapHolder());
		assertEquals("VALUE", o);

		expression = (SpelExpression) parser.parseExpression("foo[new]");
		o = expression.getValue(new MapHolder());
		assertEquals("value", o);

		expression = (SpelExpression) parser.parseExpression("foo['abc.def']");
		o = expression.getValue(new MapHolder());
		assertEquals("value", o);

		expression = (SpelExpression)parser.parseExpression("foo[foo[NEW]]");
		o = expression.getValue(new MapHolder());
		assertEquals("37",o);

		expression = (SpelExpression)parser.parseExpression("foo[foo[new]]");
		o = expression.getValue(new MapHolder());
		assertEquals("38",o);

		expression = (SpelExpression)parser.parseExpression("foo[foo[foo[T]]]");
		o = expression.getValue(new MapHolder());
		assertEquals("value",o);
	}
