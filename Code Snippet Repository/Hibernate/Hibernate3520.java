	public final void loadCollectionBatch(
			final SharedSessionContractImplementor session,
			final Serializable[] ids,
			final Type type) throws HibernateException {
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf(
					"Batch loading collection: %s",
					MessageHelper.collectionInfoString( getCollectionPersisters()[0], ids, getFactory() )
			);
		}

		Type[] idTypes = new Type[ids.length];
		Arrays.fill( idTypes, type );
		try {
			doQueryAndInitializeNonLazyCollections(
					session,
					new QueryParameters( idTypes, ids, ids ),
					true
			);
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not initialize a collection batch: " +
							MessageHelper.collectionInfoString( getCollectionPersisters()[0], ids, getFactory() ),
					getSQLString()
			);
		}

		LOG.debug( "Done batch load" );
	}
