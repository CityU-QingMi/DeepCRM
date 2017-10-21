	@Test
	@SuppressWarnings("")
	public void selectionWithMap() {
		EvaluationContext context = new StandardEvaluationContext(new MapTestBean());
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("colors.?[key.startsWith('b')]");

		Map<String, String> colorsMap = (Map<String, String>) exp.getValue(context);
		assertEquals(3, colorsMap.size());
		assertTrue(colorsMap.containsKey("beige"));
		assertTrue(colorsMap.containsKey("blue"));
		assertTrue(colorsMap.containsKey("brown"));
	}
