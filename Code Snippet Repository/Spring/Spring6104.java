	@Test
	public void mapWithAllStringValues() {
		Map<String, Object> map = new HashMap<>();
		map.put("x", "1");
		map.put("y", "2");
		map.put("z", "3");
		Expression expression = parser.parseExpression("foo(#props)");
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("props", map);
		String result = expression.getValue(context, new TestBean(), String.class);
		assertEquals("123", result);
	}
