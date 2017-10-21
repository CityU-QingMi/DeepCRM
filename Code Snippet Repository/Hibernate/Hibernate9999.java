	@Override
	public boolean mapToEntityFromMap(Object obj, Map data) {
		if ( data == null || obj == null ) {
			return false;
		}

		final Object value = data.get( propertyData.getName() );
		if ( value == null ) {
			return false;
		}

		final Setter setter = ReflectionTools.getSetter( obj.getClass(), propertyData, getServiceRegistry() );
		setter.set( obj, value, null );

		return true;
	}
