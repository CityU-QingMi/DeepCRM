	public Object replace(
			Object original, 
			Object target, 
			SharedSessionContractImplementor session, 
			Object owner, 
			Map copyCache, 
			ForeignKeyDirection foreignKeyDirection) 
	throws HibernateException {
		boolean include;
		if ( isAssociationType() ) {
			AssociationType atype = (AssociationType) this;
			include = atype.getForeignKeyDirection()==foreignKeyDirection;
		}
		else {
			include = ForeignKeyDirection.FROM_PARENT ==foreignKeyDirection;
		}
		return include ? replace(original, target, session, owner, copyCache) : target;
	}
