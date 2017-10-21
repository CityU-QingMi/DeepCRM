	@Test
	public void list() {

		assertEquals("Incorrect plural List form", "testObjectList",
				Conventions.getVariableName(Collections.singletonList(new TestObject())));

		assertEquals("Incorrect plural List form", "testObjectList",
				Conventions.getVariableNameForParameter(getMethodParameter(List.class)));

		assertEquals("Incorrect plural List form", "testObjectList",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(List.class)));
	}
