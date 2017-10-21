	public static void assertMapAttributesInMetaModelFor(Class<?> clazz, String fieldName, Class<?> expectedMapKey, Class<?> expectedMapValue, String errorString) {
		Field field = getFieldFromMetamodelFor( clazz, fieldName );
		assertNotNull( field );
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		Type actualMapKeyType = type.getActualTypeArguments()[1];
		assertEquals( buildErrorString( errorString, clazz ), expectedMapKey, actualMapKeyType );

		Type actualMapKeyValue = type.getActualTypeArguments()[2];
		assertEquals( buildErrorString( errorString, clazz ), expectedMapValue, actualMapKeyValue );
	}
