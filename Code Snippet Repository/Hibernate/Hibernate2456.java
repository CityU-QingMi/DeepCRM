	@SuppressWarnings({""})
	protected void entityIsPersistent(PersistEvent event, Map createCache) {
		LOG.trace( "Ignoring persistent instance" );
		final EventSource source = event.getSession();

		//TODO: check that entry.getIdentifier().equals(requestedId)

		final Object entity = source.getPersistenceContext().unproxy( event.getObject() );
		final EntityPersister persister = source.getEntityPersister( event.getEntityName(), entity );

		if ( createCache.put( entity, entity ) == null ) {
			justCascade( createCache, source, entity, persister );

		}
	}
