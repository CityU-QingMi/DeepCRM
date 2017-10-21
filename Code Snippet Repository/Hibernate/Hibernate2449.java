	protected void entityIsPersistent(MergeEvent event, Map copyCache) {
		LOG.trace( "Ignoring persistent instance" );

		//TODO: check that entry.getIdentifier().equals(requestedId)

		final Object entity = event.getEntity();
		final EventSource source = event.getSession();
		final EntityPersister persister = source.getEntityPersister( event.getEntityName(), entity );

		( (MergeContext) copyCache ).put( entity, entity, true );  //before cascade!

		cascadeOnMerge( source, persister, entity, copyCache );
		copyValues( persister, entity, entity, source, copyCache );

		event.setResult( entity );
	}
