	public static Object getFieldByReflection(Object entity, String fieldName) {
		try {
			Field field = entity.getClass().getDeclaredField( fieldName );
			field.setAccessible( true );
			return field.get( entity );
		}
		catch (NoSuchFieldException | IllegalAccessException e) {
			fail( "Fail to get field '" + fieldName + "' in entity " + entity + ": " + e.getMessage() );
		}
		return null;
	}
