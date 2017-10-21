	protected Field resolveField() {
		try {
			final Field field = declaringClass.getDeclaredField( fieldName );
			field.setAccessible( true );
			return field;
		}
		catch (NoSuchFieldException e) {
			throw new PropertyAccessSerializationException(
					"Unable to resolve field on deserialization : " + declaringClass.getName() + "#" + fieldName
			);
		}
	}
