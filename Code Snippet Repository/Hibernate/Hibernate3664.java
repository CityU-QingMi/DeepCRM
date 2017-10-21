	public final List loadEntityBatch(
			final SharedSessionContractImplementor session,
			final Serializable[] ids,
			final Type idType,
			final Object optionalObject,
			final String optionalEntityName,
			final Serializable optionalId,
			final EntityPersister persister,
			LockOptions lockOptions) throws HibernateException {

		if ( log.isDebugEnabled() ) {
			log.debugf( "Batch loading entity: %s", MessageHelper.infoString( persister, ids, getFactory() ) );
		}

		final Type[] types = new Type[ids.length];
		Arrays.fill( types, idType );
		List result;
		try {
			final QueryParameters qp = new QueryParameters();
			qp.setPositionalParameterTypes( types );
			qp.setPositionalParameterValues( ids );
			qp.setLockOptions( lockOptions );

			result = executeLoad(
					session,
					qp,
					staticLoadQuery,
					false,
					null
			);
		}
		catch ( SQLException sqle ) {
			throw getFactory().getSQLExceptionHelper().convert(
					sqle,
					"could not load an entity batch: " + MessageHelper.infoString( entityPersister, ids, getFactory() ),
					staticLoadQuery.getSqlStatement()
			);
		}

		log.debug( "Done entity batch load" );

		return result;

	}
