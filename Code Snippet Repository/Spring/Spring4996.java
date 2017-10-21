	@Test
	public void simpleObject() {

		assertEquals("Incorrect singular variable name",
				"testObject", Conventions.getVariableName(new TestObject()));

		assertEquals("Incorrect singular variable name", "testObject",
				Conventions.getVariableNameForParameter(getMethodParameter(TestObject.class)));

		assertEquals("Incorrect singular variable name", "testObject",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(TestObject.class)));
	}
