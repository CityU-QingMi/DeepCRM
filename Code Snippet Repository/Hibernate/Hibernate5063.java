	@Override
	public Object replace(
			Object original,
			Object target,
			SharedSessionContractImplementor session,
			Object owner,
			Map copyCache)
			throws HibernateException {
		return userType.replace( original, target, session, owner );
	}
