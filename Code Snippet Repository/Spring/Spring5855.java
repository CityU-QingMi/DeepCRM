	@Test
	public void testRootObjectConstructor() {
		EvaluationContext ctx = getContext();
		// TypedValue root = ctx.getRootObject();
		// supplied should override root on context
		ExpressionState state = new ExpressionState(ctx,new TypedValue("i am a string"));
		TypedValue stateRoot = state.getRootContextObject();
		assertEquals(String.class,stateRoot.getTypeDescriptor().getType());
		assertEquals("i am a string",stateRoot.getValue());
	}
