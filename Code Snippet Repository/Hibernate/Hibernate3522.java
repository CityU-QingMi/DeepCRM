	protected final void loadCollectionSubselect(
			final SharedSessionContractImplementor session,
			final Serializable[] ids,
			final Object[] parameterValues,
			final Type[] parameterTypes,
			final Map<String, TypedValue> namedParameters,
			final Type type) throws HibernateException {
		final Type[] idTypes = new Type[ids.length];
		Arrays.fill( idTypes, type );
		try {
			doQueryAndInitializeNonLazyCollections(
					session,
					new QueryParameters( parameterTypes, parameterValues, namedParameters, ids ),
					true
			);
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not load collection by subselect: " +
							MessageHelper.collectionInfoString( getCollectionPersisters()[0], ids, getFactory() ),
					getSQLString()
			);
		}
	}
