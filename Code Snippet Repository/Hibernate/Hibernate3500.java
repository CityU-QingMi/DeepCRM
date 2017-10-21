	private EntityKey getKeyFromResultSet(
			final int i,
			final Loadable persister,
			final Serializable id,
			final ResultSet rs,
			final SharedSessionContractImplementor session) throws HibernateException, SQLException {

		Serializable resultId;

		// if we know there is exactly 1 row, we can skip.
		// it would be great if we could _always_ skip this;
		// it is a problem for <key-many-to-one>

		if ( isSingleRowLoader() && id != null ) {
			resultId = id;
		}
		else {
			final Type idType = persister.getIdentifierType();
			resultId = (Serializable) idType.nullSafeGet(
					rs,
					getEntityAliases()[i].getSuffixedKeyAliases(),
					session,
					null //problematic for <key-many-to-one>!
			);

			final boolean idIsResultId = id != null &&
					resultId != null &&
					idType.isEqual( id, resultId, factory );

			if ( idIsResultId ) {
				resultId = id; //use the id passed in
			}
		}

		return resultId == null ? null : session.generateEntityKey( resultId, persister );
	}
