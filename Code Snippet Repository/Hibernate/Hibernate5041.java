	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory)
			throws HibernateException {
		if ( value == null ) {
			return "null";
		}

		if ( entityMode == null ) {
			throw new ClassCastException( value.getClass().getName() );
		}
		Map<String, String> result = new HashMap<>();
		Object[] values = getPropertyValues( value, entityMode );
		for ( int i = 0; i < propertyTypes.length; i++ ) {
			if ( values[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY ) {
				result.put( propertyNames[i], "<uninitialized>" );
			}
			else {
				result.put( propertyNames[i], propertyTypes[i].toLoggableString( values[i], factory ) );
			}
		}
		return StringHelper.unqualify( getName() ) + result.toString();
	}
