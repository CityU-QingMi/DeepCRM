	protected void cascadeAfterSave(
			EventSource source,
			EntityPersister persister,
			Object entity,
			Object anything) {

		// cascade-save to collections AFTER the collection owner was saved
		source.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade(
					getCascadeAction(),
					CascadePoint.AFTER_INSERT_BEFORE_DELETE,
					source,
					persister,
					entity,
					anything
			);
		}
		finally {
			source.getPersistenceContext().decrementCascadeLevel();
		}
	}
