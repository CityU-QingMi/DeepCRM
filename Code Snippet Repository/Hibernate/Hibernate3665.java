	@Override
	public Object load(Serializable id, Object optionalObject, SharedSessionContractImplementor session, LockOptions lockOptions) {

		final Object result;
		try {
			final QueryParameters qp = new QueryParameters();
			qp.setPositionalParameterTypes( new Type[] { entityPersister.getIdentifierType() } );
			qp.setPositionalParameterValues( new Object[] { id } );
			qp.setOptionalObject( optionalObject );
			qp.setOptionalEntityName( entityPersister.getEntityName() );
			qp.setOptionalId( id );
			qp.setLockOptions( lockOptions );

			final List results = executeLoad(
					session,
					qp,
					staticLoadQuery,
					false,
					null
			);
			result = extractEntityResult( results );
		}
		catch ( SQLException sqle ) {
			throw session.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not load an entity: " + MessageHelper.infoString(
							entityPersister,
							id,
							entityPersister.getIdentifierType(),
							getFactory()
					),
					staticLoadQuery.getSqlStatement()
			);
		}

		log.debugf( "Done entity load : %s#%s", getEntityName(), id );
		return result;
	}
