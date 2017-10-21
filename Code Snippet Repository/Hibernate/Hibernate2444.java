	private void cascadeOnLock(LockEvent event, EntityPersister persister, Object entity) {
		EventSource source = event.getSession();
		source.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade(
					CascadingActions.LOCK,
					CascadePoint.AFTER_LOCK,
					source,
					persister,
					entity,
					event.getLockOptions()
			);
		}
		finally {
			source.getPersistenceContext().decrementCascadeLevel();
		}
	}
