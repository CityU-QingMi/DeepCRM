	protected void cascadeOnMerge(
			final EventSource source,
			final EntityPersister persister,
			final Object entity,
			final Map copyCache
	) {
		source.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade(
					getCascadeAction(),
					CascadePoint.BEFORE_MERGE,
					source,
					persister,
					entity,
					copyCache
			);
		}
		finally {
			source.getPersistenceContext().decrementCascadeLevel();
		}
	}
