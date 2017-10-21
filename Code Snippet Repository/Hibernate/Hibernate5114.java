	@Override
	public Object hydrate(
			ResultSet rs,
			String[] names,
			SharedSessionContractImplementor session,
			Object owner) throws HibernateException, SQLException {
		// return the (fully resolved) identifier value, but do not resolve
		// to the actual referenced entity instance
		// NOTE: the owner of the association is not really the owner of the id!

		// First hydrate the ID to check if it is null.
		// Don't bother resolving the ID if hydratedKeyState[i] is null.

		// Implementation note: if id is a composite ID, then resolving a null value will
		// result in instantiating an empty composite if AvailableSettings#CREATE_EMPTY_COMPOSITES_ENABLED
		// is true. By not resolving a null value for a composite ID, we avoid the overhead of instantiating
		// an empty composite, checking if it is equivalent to null (it should be), then ultimately throwing
		// out the empty value.
		final Object hydratedId = getIdentifierOrUniqueKeyType( session.getFactory() )
				.hydrate( rs, names, session, null );
		final Serializable id;
		if ( hydratedId != null ) {
			id = (Serializable) getIdentifierOrUniqueKeyType( session.getFactory() )
					.resolve( hydratedId, session, null );
		}
		else {
			id = null;
		}
		scheduleBatchLoadIfNeeded( id, session );
		return id;
	}
