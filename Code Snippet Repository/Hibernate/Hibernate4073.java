	@Override
	public Object readKey(ResultSet rs, String[] aliases, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// First hydrate the collection key to check if it is null.
		// Don't bother resolving the collection key if the hydrated value is null.

		// Implementation note: if collection key is a composite value, then resolving a null value will
		// result in instantiating an empty composite if AvailableSettings#CREATE_EMPTY_COMPOSITES_ENABLED
		// is true. By not resolving a null value for a composite key, we avoid the overhead of instantiating
		// an empty composite, checking if it is equivalent to null (it should be), then ultimately throwing
		// out the empty value.
		final Object hydratedKey = getKeyType().hydrate( rs, aliases, session, null );
		return hydratedKey == null ? null : getKeyType().resolve( hydratedKey, session, null );
	}
