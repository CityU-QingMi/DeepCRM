	@Test
	public void mapOfMap_SPR7244() throws Exception {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("uri", "http:");
		Map<String, String> nameMap = new LinkedHashMap<>();
		nameMap.put("givenName", "Arthur");
		map.put("value", nameMap);

		StandardEvaluationContext ctx = new StandardEvaluationContext(map);
		ExpressionParser parser = new SpelExpressionParser();
		String el1 = "#root['value'].get('givenName')";
		Expression exp = parser.parseExpression(el1);
		Object evaluated = exp.getValue(ctx);
		assertEquals("Arthur", evaluated);

		String el2 = "#root['value']['givenName']";
		exp = parser.parseExpression(el2);
		evaluated = exp.getValue(ctx);
		assertEquals("Arthur", evaluated);
	}
