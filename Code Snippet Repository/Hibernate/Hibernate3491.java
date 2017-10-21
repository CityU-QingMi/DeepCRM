	private Object getRowFromResultSet(
			final ResultSet resultSet,
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final LockMode[] lockModesArray,
			final EntityKey optionalObjectKey,
			final List hydratedObjects,
			final EntityKey[] keys,
			boolean returnProxies) throws SQLException, HibernateException {
		return getRowFromResultSet(
				resultSet,
				session,
				queryParameters,
				lockModesArray,
				optionalObjectKey,
				hydratedObjects,
				keys,
				returnProxies,
				null
		);
	}
