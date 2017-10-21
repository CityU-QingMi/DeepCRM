	public static void assertAttributeTypeInMetaModelFor(Class<?> clazz, String fieldName, Class<?> expectedType, String errorString) {
		Field field = getFieldFromMetamodelFor( clazz, fieldName );
		assertNotNull( "Cannot find field '" + fieldName + "' in " + clazz.getName(), field );
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		Type actualType = type.getActualTypeArguments()[1];
		if ( expectedType.isArray() ) {
			expectedType = expectedType.getComponentType();
			actualType = getComponentType( actualType );
		}
		assertEquals(
				"Types do not match: " + buildErrorString( errorString, clazz ),
				expectedType,
				actualType
		);
	}
