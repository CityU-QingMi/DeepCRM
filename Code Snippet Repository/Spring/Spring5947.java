	@Test
	@SuppressWarnings("")
	public void selectFirstItemInMap() {
		EvaluationContext context = new StandardEvaluationContext(new MapTestBean());
		ExpressionParser parser = new SpelExpressionParser();

		Expression exp = parser.parseExpression("colors.^[key.startsWith('b')]");
		Map<String, String> colorsMap = (Map<String, String>) exp.getValue(context);
		assertEquals(1, colorsMap.size());
		assertEquals("beige", colorsMap.keySet().iterator().next());
	}
