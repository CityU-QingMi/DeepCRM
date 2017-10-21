	@Test
	public void projectionWithArray() throws Exception {
		Expression expression = new SpelExpressionParser().parseRaw("#testArray.![wrapper.value]");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("testArray", IntegerTestBean.createArray());
		Object value = expression.getValue(context);
		assertTrue(value.getClass().isArray());
		TypedValue typedValue = new TypedValue(value);
		assertEquals(Number.class, typedValue.getTypeDescriptor().getElementTypeDescriptor().getType());
		Number[] array = (Number[]) value;
		assertEquals(3, array.length);
		assertEquals(new Integer(5), array[0]);
		assertEquals(5.9f, array[1]);
		assertEquals(new Integer(7), array[2]);
	}
