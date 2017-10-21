	@Override
	public void initialize(Serializable id, SharedSessionContractImplementor session)
			throws HibernateException {
		if ( log.isDebugEnabled() ) {
			log.debugf( "Loading collection: %s",
					MessageHelper.collectionInfoString( collectionPersister, id, getFactory() ) );
		}


		final Serializable[] ids = new Serializable[]{id};
		try {
			final QueryParameters qp = new QueryParameters();
			qp.setPositionalParameterTypes( new Type[]{ collectionPersister.getKeyType() } );
			qp.setPositionalParameterValues( ids );
			qp.setCollectionKeys( ids );

			qp.setLockOptions( lockOptions );

			executeLoad(
					session,
					qp,
					staticLoadQuery,
					true,
					null

			);
		}
		catch ( SQLException sqle ) {
			throw session.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not initialize a collection: " +
							MessageHelper.collectionInfoString( collectionPersister, id, getFactory() ),
					staticLoadQuery.getSqlStatement()
			);
		}

		log.debug( "Done loading collection" );
	}
