	@Test
	void findMethodByParameterNamesWithParameterizedMapParameter() throws Exception {
		String methodName = "methodWithParameterizedMap";

		// standard, supported use case
		assertFindMethodByParameterNames(methodName, Map.class);

		// generic type info in parameter list
		Method method = getClass().getDeclaredMethod(methodName, Map.class);
		String genericParameterTypeName = method.getGenericParameterTypes()[0].getTypeName();
		JUnitException exception = assertThrows(JUnitException.class,
			() -> findMethod(getClass(), methodName, genericParameterTypeName));

		assertThat(exception).hasMessageStartingWith("Failed to load parameter type [java.util.Map<java.lang.String");
	}
