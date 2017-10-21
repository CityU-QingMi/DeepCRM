	@Test
	public void indexIntoGenericPropertyContainingMapObject() {
		Map<String, Map<String, String>> property = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		map.put("foo", "bar");
		property.put("property", map);
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.addPropertyAccessor(new MapAccessor());
		context.setRootObject(property);
		Expression expression = parser.parseExpression("property");
		assertEquals("java.util.HashMap<?, ?>", expression.getValueTypeDescriptor(context).toString());
		assertEquals(map, expression.getValue(context));
		assertEquals(map, expression.getValue(context, Map.class));
		expression = parser.parseExpression("property['foo']");
		assertEquals("bar", expression.getValue(context));
	}
