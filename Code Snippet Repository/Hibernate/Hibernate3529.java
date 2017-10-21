	public Object loadSingleRow(
			final ResultSet resultSet,
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final boolean returnProxies) throws HibernateException {

		final int entitySpan = getEntityPersisters().length;
		final List hydratedObjects = entitySpan == 0 ?
				null : new ArrayList( entitySpan );

		final Object result;
		try {
			result = getRowFromResultSet(
					resultSet,
					session,
					queryParameters,
					getLockModes( queryParameters.getLockOptions() ),
					null,
					hydratedObjects,
					new EntityKey[entitySpan],
					returnProxies
			);
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not read next row of results",
					getSQLString()
			);
		}

		initializeEntitiesAndCollections(
				hydratedObjects,
				resultSet,
				session,
				queryParameters.isReadOnly( session )
		);
		session.getPersistenceContext().initializeNonLazyCollections();
		return result;
	}
