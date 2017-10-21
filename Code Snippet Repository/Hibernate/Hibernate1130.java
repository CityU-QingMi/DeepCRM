	@Override
	public void setPropertyValues(Object object, Object[] values) {
		try {
			bulkAccessor.setPropertyValues( object, values );
		}
		catch ( Throwable t ) {
			throw new PropertyAccessException(
					t,
					PROPERTY_SET_EXCEPTION,
					true,
					mappedClass,
					setterName( t, bulkAccessor )
			);
		}
	}
