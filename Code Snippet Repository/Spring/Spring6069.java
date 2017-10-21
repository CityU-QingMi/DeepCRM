	@Test
	public void SPR9194() {
		TestClass2 one = new TestClass2("abc");
		TestClass2 two = new TestClass2("abc");
		Map<String, TestClass2> map = new HashMap<>();
		map.put("one", one);
		map.put("two", two);

		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression("['one'] == ['two']");
		assertTrue(expr.getValue(map, Boolean.class));
	}
