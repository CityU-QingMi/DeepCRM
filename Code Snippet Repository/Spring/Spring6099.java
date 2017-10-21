	@Test
	public void test_binaryPlusWithLeftStringOperand() {
		ExpressionState expressionState = new ExpressionState(new StandardEvaluationContext());

		StringLiteral n1 = new StringLiteral("\"number is \"", -1, "\"number is \"");
		LongLiteral n2 = new LongLiteral("123", -1, 123);
		OpPlus o = new OpPlus(-1, n1, n2);
		TypedValue value = o.getValueInternal(expressionState);

		assertEquals(String.class, value.getTypeDescriptor().getObjectType());
		assertEquals(String.class, value.getTypeDescriptor().getType());
		assertEquals("number is 123", value.getValue());
	}
