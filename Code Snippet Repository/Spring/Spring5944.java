	@Test
	public void selectionWithArray() throws Exception {
		Expression expression = new SpelExpressionParser().parseRaw("integers.?[#this<5]");
		EvaluationContext context = new StandardEvaluationContext(new ArrayTestBean());
		Object value = expression.getValue(context);
		assertTrue(value.getClass().isArray());
		TypedValue typedValue = new TypedValue(value);
		assertEquals(Integer.class, typedValue.getTypeDescriptor().getElementTypeDescriptor().getType());
		Integer[] array = (Integer[]) value;
		assertEquals(5, array.length);
		assertEquals(new Integer(0), array[0]);
		assertEquals(new Integer(1), array[1]);
		assertEquals(new Integer(2), array[2]);
		assertEquals(new Integer(3), array[3]);
		assertEquals(new Integer(4), array[4]);
	}
