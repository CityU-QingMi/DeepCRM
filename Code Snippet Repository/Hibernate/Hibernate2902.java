	@Override
	public InsertGeneratedIdentifierDelegate getInsertGeneratedIdentifierDelegate(
			PostInsertIdentityPersister persister,
			Dialect dialect,
			boolean isGetGeneratedKeysEnabled) throws HibernateException {
		if ( isGetGeneratedKeysEnabled ) {
			return dialect.getIdentityColumnSupport().buildGetGeneratedKeysDelegate( persister, dialect );
		}
		else if ( dialect.getIdentityColumnSupport().supportsInsertSelectIdentity() ) {
			return new InsertSelectDelegate( persister, dialect );
		}
		else {
			return new BasicDelegate( persister, dialect );
		}
	}
