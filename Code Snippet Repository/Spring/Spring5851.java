	@Test
	public void testLocalVariableNestedScopes() {
		ExpressionState state = getState();
		assertEquals(null,state.lookupLocalVariable("foo"));

		state.setLocalVariable("foo",12);
		assertEquals(12,state.lookupLocalVariable("foo"));

		state.enterScope(null);
		assertEquals(12,state.lookupLocalVariable("foo")); // found in upper scope

		state.setLocalVariable("foo","abc");
		assertEquals("abc",state.lookupLocalVariable("foo")); // found in nested scope

		state.exitScope();
		assertEquals(12,state.lookupLocalVariable("foo")); // found in nested scope
	}
