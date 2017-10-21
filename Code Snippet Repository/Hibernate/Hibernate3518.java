	public final List loadEntityBatch(
			final SharedSessionContractImplementor session,
			final Serializable[] ids,
			final Type idType,
			final Object optionalObject,
			final String optionalEntityName,
			final Serializable optionalId,
			final EntityPersister persister,
			LockOptions lockOptions) throws HibernateException {
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Batch loading entity: %s", MessageHelper.infoString( persister, ids, getFactory() ) );
		}

		Type[] types = new Type[ids.length];
		Arrays.fill( types, idType );
		List result;
		try {
			QueryParameters qp = new QueryParameters();
			qp.setPositionalParameterTypes( types );
			qp.setPositionalParameterValues( ids );
			qp.setOptionalObject( optionalObject );
			qp.setOptionalEntityName( optionalEntityName );
			qp.setOptionalId( optionalId );
			qp.setLockOptions( lockOptions );
			result = doQueryAndInitializeNonLazyCollections( session, qp, false );
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not load an entity batch: " +
							MessageHelper.infoString( getEntityPersisters()[0], ids, getFactory() ),
					getSQLString()
			);
		}

		LOG.debug( "Done entity batch load" );

		return result;

	}
