	@Test
	public void testPopulatedNestedScopesMap() {
		ExpressionState state = getState();
		assertNull(state.lookupLocalVariable("foo"));
		assertNull(state.lookupLocalVariable("goo"));

		Map<String,Object> m = new HashMap<>();
		m.put("foo",34);
		m.put("goo","abc");

		state.enterScope(m);
		assertEquals(34,state.lookupLocalVariable("foo"));
		assertEquals("abc",state.lookupLocalVariable("goo"));

		state.enterScope(null);
		state.setLocalVariable("foo",12);
		assertEquals(12,state.lookupLocalVariable("foo"));
		assertEquals("abc",state.lookupLocalVariable("goo"));

		state.exitScope();
		state.exitScope();
		assertNull(state.lookupLocalVariable("foo"));
		assertNull(state.lookupLocalVariable("goo"));
	}
