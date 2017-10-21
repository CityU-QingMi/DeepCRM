	@Test
	public void test_binaryPlusWithRightStringOperand() {
		ExpressionState expressionState = new ExpressionState(new StandardEvaluationContext());

		LongLiteral n1 = new LongLiteral("123", -1, 123);
		StringLiteral n2 = new StringLiteral("\" is a number\"", -1, "\" is a number\"");
		OpPlus o = new OpPlus(-1, n1, n2);
		TypedValue value = o.getValueInternal(expressionState);

		assertEquals(String.class, value.getTypeDescriptor().getObjectType());
		assertEquals(String.class, value.getTypeDescriptor().getType());
		assertEquals("123 is a number", value.getValue());
	}
