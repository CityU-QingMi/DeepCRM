	protected void cascadeBeforeSave(
			EventSource source,
			EntityPersister persister,
			Object entity,
			Object anything) {

		// cascade-save to many-to-one BEFORE the parent is saved
		source.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade(
					getCascadeAction(),
					CascadePoint.BEFORE_INSERT_AFTER_DELETE,
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
