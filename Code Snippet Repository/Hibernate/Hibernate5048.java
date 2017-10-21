	@Override
	public Object assemble(Serializable object, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {

		if ( object == null ) {
			return null;
		}
		else {
			Object[] values = (Object[]) object;
			Object[] assembled = new Object[values.length];
			for ( int i = 0; i < propertyTypes.length; i++ ) {
				assembled[i] = propertyTypes[i].assemble( (Serializable) values[i], session, owner );
			}
			Object result = instantiate( owner, session );
			setPropertyValues( result, assembled, entityMode );
			return result;
		}
	}
