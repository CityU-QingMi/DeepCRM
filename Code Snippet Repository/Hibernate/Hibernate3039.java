	public Filter setParameterList(String name, Collection values) throws HibernateException  {
		// Make sure this is a defined parameter and check the incoming value type
		if ( values == null ) {
			throw new IllegalArgumentException( "Collection must be not null!" );
		}
		Type type = definition.getParameterType( name );
		if ( type == null ) {
			throw new HibernateException( "Undefined filter parameter [" + name + "]" );
		}
		if ( !values.isEmpty() ) {
			Class elementClass = values.iterator().next().getClass();
			if ( !type.getReturnedClass().isAssignableFrom( elementClass ) ) {
				throw new HibernateException( "Incorrect type for parameter [" + name + "]" );
			}
		}
		parameters.put( name, values );
		return this;
	}
