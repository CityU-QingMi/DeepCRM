	@Test
	public void testLocalVariables() {
		ExpressionState state = getState();

		Object value = state.lookupLocalVariable("foo");
		assertNull(value);

		state.setLocalVariable("foo",34);
		value = state.lookupLocalVariable("foo");
		assertEquals(34,value);

		state.setLocalVariable("foo",null);
		value = state.lookupLocalVariable("foo");
		assertEquals(null,value);
	}
