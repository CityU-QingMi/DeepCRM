	@SuppressWarnings({""})
	private void entityIsDeleted(PersistEvent event, Map createCache) {
		final EventSource source = event.getSession();

		final Object entity = source.getPersistenceContext().unproxy( event.getObject() );
		final EntityPersister persister = source.getEntityPersister( event.getEntityName(), entity );

		LOG.tracef(
				"un-scheduling entity deletion [%s]",
				MessageHelper.infoString(
						persister,
						persister.getIdentifier( entity, source ),
						source.getFactory()
				)
		);

		if ( createCache.put( entity, entity ) == null ) {
			justCascade( createCache, source, entity, persister );
		}
	}
