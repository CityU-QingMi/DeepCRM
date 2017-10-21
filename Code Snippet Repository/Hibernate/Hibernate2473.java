	protected void entityIsDetached(SaveOrUpdateEvent event) {

		LOG.trace( "Updating detached instance" );

		if ( event.getSession().getPersistenceContext().isEntryFor( event.getEntity() ) ) {
			//TODO: assertion only, could be optimized away
			throw new AssertionFailure( "entity was persistent" );
		}

		Object entity = event.getEntity();

		EntityPersister persister = event.getSession().getEntityPersister( event.getEntityName(), entity );

		event.setRequestedId(
				getUpdateId(
						entity, persister, event.getRequestedId(), event.getSession()
				)
		);

		performUpdate( event, entity, persister );

	}
