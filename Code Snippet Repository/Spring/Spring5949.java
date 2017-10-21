	@Test
	public void projectionWithList() throws Exception {
		Expression expression = new SpelExpressionParser().parseRaw("#testList.![wrapper.value]");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("testList", IntegerTestBean.createList());
		Object value = expression.getValue(context);
		assertTrue(value instanceof List);
		List<?> list = (List<?>) value;
		assertEquals(3, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(7, list.get(2));
	}
