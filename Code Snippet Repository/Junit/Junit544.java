	@Disabled("")
	@Test
	void executeTestsForParameterizedTypesSelectingByFullyQualifiedMethodNameContainingGenericInfo() throws Exception {
		Method method = ParameterizedTypeTestCase.class.getDeclaredMethod("testMapOfStrings", Map.class);
		String genericParameterTypeName = method.getGenericParameterTypes()[0].getTypeName();
		String fqmn = String.format("%s#%s(%s)", ParameterizedTypeTestCase.class.getName(), "testMapOfStrings",
			genericParameterTypeName);

		assertEventsForParameterizedTypes(executeTests(request().selectors(selectMethod(fqmn)).build()));
	}
