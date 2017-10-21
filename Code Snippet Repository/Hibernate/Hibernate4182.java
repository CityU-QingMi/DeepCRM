	@Override
	public Object load(Serializable id, Object optionalObject, SharedSessionContractImplementor session) {
		LOG.debugf( "Loading entity: %s using named query: %s", persister.getEntityName(), queryName );

		// IMPL NOTE: essentially we perform the named query (which loads the entity into the PC), and then
		// do an internal lookup of the entity from the PC.

		final AbstractProducedQuery query = (AbstractProducedQuery) session.getNamedQuery( queryName );
		if ( query.getParameterMetadata().hasNamedParameters() ) {
			query.setParameter( query.getNamedParameters()[0], id, persister.getIdentifierType() );
		}
		else {
			query.setParameter( 0, id, persister.getIdentifierType() );
		}

		query.setOptionalId( id );
		query.setOptionalEntityName( persister.getEntityName() );
		query.setOptionalObject( optionalObject );
		query.setFlushMode( FlushMode.MANUAL );
		query.list();

		// now look up the object we are really interested in!
		// (this lets us correctly handle proxies and multi-row or multi-column queries)
		return session.getPersistenceContext().getEntity( session.generateEntityKey( id, persister ) );

	}
