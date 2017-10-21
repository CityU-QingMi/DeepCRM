	@Test
	public void testRootContextObject() {
		ExpressionState state = getState();
		assertEquals(Inventor.class,state.getRootContextObject().getValue().getClass());

		// although the root object is being set on the evaluation context, the value in the 'state' remains what it was when constructed
		((StandardEvaluationContext) state.getEvaluationContext()).setRootObject(null);
		assertEquals(Inventor.class,state.getRootContextObject().getValue().getClass());
		// assertEquals(null, state.getRootContextObject().getValue());

		state = new ExpressionState(new StandardEvaluationContext());
		assertEquals(TypedValue.NULL,state.getRootContextObject());


		((StandardEvaluationContext)state.getEvaluationContext()).setRootObject(null);
		assertEquals(null,state.getRootContextObject().getValue());
	}
