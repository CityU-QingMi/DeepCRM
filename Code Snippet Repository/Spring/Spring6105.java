	@Test
	public void mapWithNonStringValue() {
		Map<String, Object> map = new HashMap<>();
		map.put("x", "1");
		map.put("y", 2);
		map.put("z", "3");
		map.put("a", new UUID(1, 1));
		Expression expression = parser.parseExpression("foo(#props)");
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("props", map);
		String result = expression.getValue(context, new TestBean(), String.class);
		assertEquals("1null3", result);
	}
