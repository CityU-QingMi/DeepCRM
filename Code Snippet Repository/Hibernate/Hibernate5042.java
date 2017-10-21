	@Override
	public Object deepCopy(Object component, SessionFactoryImplementor factory)
			throws HibernateException {
		if ( component == null ) {
			return null;
		}

		Object[] values = getPropertyValues( component, entityMode );
		for ( int i = 0; i < propertySpan; i++ ) {
			values[i] = propertyTypes[i].deepCopy( values[i], factory );
		}

		Object result = instantiate( entityMode );
		setPropertyValues( result, values, entityMode );

		//not absolutely necessary, but helps for some
		//equals()/hashCode() implementations
		if ( componentTuplizer.hasParentProperty() ) {
			componentTuplizer.setParent( result, componentTuplizer.getParent( component ), factory );
		}

		return result;
	}
