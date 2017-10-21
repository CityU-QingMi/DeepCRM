	public final void loadCollection(
			final SharedSessionContractImplementor session,
			final Serializable id,
			final Type type) throws HibernateException {
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf(
					"Loading collection: %s",
					MessageHelper.collectionInfoString( getCollectionPersisters()[0], id, getFactory() )
			);
		}

		Serializable[] ids = new Serializable[] {id};
		try {
			doQueryAndInitializeNonLazyCollections(
					session,
					new QueryParameters( new Type[] {type}, ids, ids ),
					true
			);
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not initialize a collection: " +
							MessageHelper.collectionInfoString( getCollectionPersisters()[0], id, getFactory() ),
					getSQLString()
			);
		}

		LOG.debug( "Done loading collection" );
	}
