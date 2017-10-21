	@Test
	public void testPopulatedNestedScopes() {
		ExpressionState state = getState();
		assertNull(state.lookupLocalVariable("foo"));

		state.enterScope("foo",34);
		assertEquals(34,state.lookupLocalVariable("foo"));

		state.enterScope(null);
		state.setLocalVariable("foo",12);
		assertEquals(12,state.lookupLocalVariable("foo"));

		state.exitScope();
		assertEquals(34,state.lookupLocalVariable("foo"));

		state.exitScope();
		assertNull(state.lookupLocalVariable("goo"));
	}
