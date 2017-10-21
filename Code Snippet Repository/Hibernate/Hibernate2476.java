	private void cascadeOnUpdate(SaveOrUpdateEvent event, EntityPersister persister, Object entity) {
		final EventSource source = event.getSession();
		source.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade( CascadingActions.SAVE_UPDATE, CascadePoint.AFTER_UPDATE, source, persister, entity );
		}
		finally {
			source.getPersistenceContext().decrementCascadeLevel();
		}
	}
