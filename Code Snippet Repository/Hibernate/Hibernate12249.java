	public static Field getFieldFromMetamodelFor(Class<?> entityClass, String fieldName) {
		Class<?> metaModelClass = getMetamodelClassFor( entityClass );
		Field field;
		try {
			field = metaModelClass.getDeclaredField( fieldName );
		}
		catch ( NoSuchFieldException e ) {
			field = null;
		}
		return field;
	}
