	@Test
	public void testVariables() {
		ExpressionState state = getState();
		TypedValue typedValue = state.lookupVariable("foo");
		assertEquals(TypedValue.NULL,typedValue);

		state.setVariable("foo",34);
		typedValue = state.lookupVariable("foo");
		assertEquals(34,typedValue.getValue());
		assertEquals(Integer.class,typedValue.getTypeDescriptor().getType());

		state.setVariable("foo","abc");
		typedValue = state.lookupVariable("foo");
		assertEquals("abc",typedValue.getValue());
		assertEquals(String.class,typedValue.getTypeDescriptor().getType());
	}
