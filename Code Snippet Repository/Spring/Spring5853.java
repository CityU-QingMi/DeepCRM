	@Test
	public void testActiveContextObject() {
		ExpressionState state = getState();
		assertEquals(state.getRootContextObject().getValue(),state.getActiveContextObject().getValue());

		try {
			state.popActiveContextObject();
			fail("stack should be empty...");
		}
		catch (EmptyStackException ese) {
			// success
		}

		state.pushActiveContextObject(new TypedValue(34));
		assertEquals(34,state.getActiveContextObject().getValue());

		state.pushActiveContextObject(new TypedValue("hello"));
		assertEquals("hello",state.getActiveContextObject().getValue());

		state.popActiveContextObject();
		assertEquals(34,state.getActiveContextObject().getValue());

		state.popActiveContextObject();
		assertEquals(state.getRootContextObject().getValue(),state.getActiveContextObject().getValue());

		state = new ExpressionState(new StandardEvaluationContext());
		assertEquals(TypedValue.NULL,state.getActiveContextObject());
	}
