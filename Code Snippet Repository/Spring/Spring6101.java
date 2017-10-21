	@Test
	public void test_binaryPlusWithTime_ToString() {

		ExpressionState expressionState = new ExpressionState(new StandardEvaluationContext());

		Time time = new Time(new Date().getTime());

		VariableReference var = new VariableReference("timeVar", -1);
		var.setValue(expressionState, time);

		StringLiteral n2 = new StringLiteral("\" is now\"", -1, "\" is now\"");
		OpPlus o = new OpPlus(-1, var, n2);
		TypedValue value = o.getValueInternal(expressionState);

		assertEquals(String.class, value.getTypeDescriptor().getObjectType());
		assertEquals(String.class, value.getTypeDescriptor().getType());
		assertEquals(time + " is now", value.getValue());
	}
