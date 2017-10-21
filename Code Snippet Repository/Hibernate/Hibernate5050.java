	@Override
	public Object resolve(Object value, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {

		if ( value != null ) {
			Object result = instantiate( owner, session );
			Object[] values = (Object[]) value;
			Object[] resolvedValues = new Object[values.length]; //only really need new array during semiresolve!
			for ( int i = 0; i < values.length; i++ ) {
				resolvedValues[i] = propertyTypes[i].resolve( values[i], session, owner );
			}
			setPropertyValues( result, resolvedValues, entityMode );
			return result;
		}
		else if ( isCreateEmptyCompositesEnabled() ) {
			return instantiate( owner, session );
		}
		else {
			return null;
		}
	}
