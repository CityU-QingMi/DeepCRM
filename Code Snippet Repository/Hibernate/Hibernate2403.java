	protected void deleteTransientEntity(
			EventSource session,
			Object entity,
			boolean cascadeDeleteEnabled,
			EntityPersister persister,
			Set transientEntities) {
		LOG.handlingTransientEntity();
		if ( transientEntities.contains( entity ) ) {
			LOG.trace( "Already handled transient entity; skipping" );
			return;
		}
		transientEntities.add( entity );
		cascadeBeforeDelete( session, persister, entity, null, transientEntities );
		cascadeAfterDelete( session, persister, entity, transientEntities );
	}
