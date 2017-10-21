	@Test
	public void selectionWithList() throws Exception {
		Expression expression = new SpelExpressionParser().parseRaw("integers.?[#this<5]");
		EvaluationContext context = new StandardEvaluationContext(new ListTestBean());
		Object value = expression.getValue(context);
		assertTrue(value instanceof List);
		List<?> list = (List<?>) value;
		assertEquals(5, list.size());
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.get(3));
		assertEquals(4, list.get(4));
	}
