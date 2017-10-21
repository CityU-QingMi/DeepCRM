	@Override
	public Object[] getPropertyValues(Object object) {
		try {
			return bulkAccessor.getPropertyValues( object );
		}
		catch ( Throwable t ) {
			throw new PropertyAccessException(
					t,
					PROPERTY_GET_EXCEPTION,
					false,
					mappedClass,
					getterName( t, bulkAccessor )
			);
		}
	}
