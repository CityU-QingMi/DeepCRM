	@Test
	public void testNoVariableInteference() {
		ExpressionState state = getState();
		TypedValue typedValue = state.lookupVariable("foo");
		assertEquals(TypedValue.NULL,typedValue);

		state.setLocalVariable("foo",34);
		typedValue = state.lookupVariable("foo");
		assertEquals(TypedValue.NULL,typedValue);

		state.setVariable("goo","hello");
		assertNull(state.lookupLocalVariable("goo"));
	}
