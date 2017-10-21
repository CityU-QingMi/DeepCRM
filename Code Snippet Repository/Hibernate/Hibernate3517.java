	protected final List loadEntity(
			final SharedSessionContractImplementor session,
			final Object key,
			final Object index,
			final Type keyType,
			final Type indexType,
			final EntityPersister persister) throws HibernateException {
		LOG.debug( "Loading collection element by index" );

		List result;
		try {
			result = doQueryAndInitializeNonLazyCollections(
					session,
					new QueryParameters(
							new Type[] {keyType, indexType},
							new Object[] {key, index}
					),
					false
			);
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not load collection element by index",
					getSQLString()
			);
		}

		LOG.debug( "Done entity load" );

		return result;

	}
