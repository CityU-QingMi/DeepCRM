	private void cascadeAfterReplicate(
			Object entity,
			EntityPersister persister,
			ReplicationMode replicationMode,
			EventSource source) {
		source.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade(
					CascadingActions.REPLICATE,
					CascadePoint.AFTER_UPDATE,
					source,
					persister,
					entity,
					replicationMode
			);
		}
		finally {
			source.getPersistenceContext().decrementCascadeLevel();
		}
	}
