	@Override
	public Object replace(
			Object original,
			Object target,
			SharedSessionContractImplementor session,
			Object owner,
			Map copyCache,
			ForeignKeyDirection foreignKeyDirection)
			throws HibernateException {

		if ( original == null ) {
			return null;
		}
		//if ( original == target ) return target;

		final Object result = target == null ?
				instantiate( owner, session ) :
				target;

		Object[] values = TypeHelper.replace(
				getPropertyValues( original, entityMode ),
				getPropertyValues( result, entityMode ),
				propertyTypes,
				session,
				owner,
				copyCache,
				foreignKeyDirection
		);

		setPropertyValues( result, values, entityMode );
		return result;
	}
