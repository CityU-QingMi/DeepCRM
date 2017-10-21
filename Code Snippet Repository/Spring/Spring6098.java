	@Test
	public void test_binaryPlusWithStringOperands() {
		ExpressionState expressionState = new ExpressionState(new StandardEvaluationContext());

		StringLiteral n1 = new StringLiteral("\"foo\"", -1, "\"foo\"");
		StringLiteral n2 = new StringLiteral("\"bar\"", -1, "\"bar\"");
		OpPlus o = new OpPlus(-1, n1, n2);
		TypedValue value = o.getValueInternal(expressionState);

		assertEquals(String.class, value.getTypeDescriptor().getObjectType());
		assertEquals(String.class, value.getTypeDescriptor().getType());
		assertEquals("foobar", value.getValue());
	}
