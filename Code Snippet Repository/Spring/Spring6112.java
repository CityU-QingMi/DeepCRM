	@Test
	public void valueType() {
		SpelExpressionParser parser = new SpelExpressionParser();
		EvaluationContext ctx = new StandardEvaluationContext();
		Class<?> c = parser.parseRaw("2").getValueType();
		assertEquals(Integer.class, c);
		c = parser.parseRaw("12").getValueType(ctx);
		assertEquals(Integer.class, c);
		c = parser.parseRaw("null").getValueType();
		assertNull(c);
		c = parser.parseRaw("null").getValueType(ctx);
		assertNull(c);
		Object o = parser.parseRaw("null").getValue(ctx, Integer.class);
		assertNull(o);
	}
