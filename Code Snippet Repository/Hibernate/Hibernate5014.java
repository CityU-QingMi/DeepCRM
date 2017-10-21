	@Override
	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner, Map copyCache)
			throws HibernateException {
		if ( original == null ) {
			return null;
		}
		else {
			final String entityName = session.bestGuessEntityName( original );
			final Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved( entityName, original, session );
			return session.internalLoad( entityName, id, false, false );
		}
	}
