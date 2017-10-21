	@Test
	public void set() {

		assertEquals("Incorrect plural Set form", "testObjectList",
				Conventions.getVariableName(Collections.singleton(new TestObject())));

		assertEquals("Incorrect plural Set form", "testObjectList",
				Conventions.getVariableNameForParameter(getMethodParameter(Set.class)));

		assertEquals("Incorrect plural Set form", "testObjectList",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(Set.class)));
	}
