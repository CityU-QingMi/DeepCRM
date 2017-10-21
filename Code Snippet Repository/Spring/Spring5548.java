	@Test
	public void withWildcardTypes() throws Exception {
		ParameterizedType openObjectsType = (ParameterizedType) getClass().getField("openObjects").getGenericType();
		ParameterizedType openNumbersType = (ParameterizedType) getClass().getField("openNumbers").getGenericType();
		Type storableObjectListType = getClass().getField("storableObjectList").getGenericType();

		Type objectType = getClass().getField("object").getGenericType();
		Type numberType = getClass().getField("number").getGenericType();
		Type stringType = getClass().getField("string").getGenericType();

		Type openWildcard = openObjectsType.getActualTypeArguments()[0]; // '?'
		Type openNumbersWildcard = openNumbersType.getActualTypeArguments()[0]; // '? extends number'

		assertTrue(TypeUtils.isAssignable(openWildcard, objectType));
		assertTrue(TypeUtils.isAssignable(openNumbersWildcard, numberType));
		assertFalse(TypeUtils.isAssignable(openNumbersWildcard, stringType));
		assertFalse(TypeUtils.isAssignable(storableObjectListType, openObjectsType));
	}
