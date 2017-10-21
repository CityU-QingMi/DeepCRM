	public MutableEntityEntry(
			final Status status,
			final Object[] loadedState,
			final Object rowId,
			final Serializable id,
			final Object version,
			final LockMode lockMode,
			final boolean existsInDatabase,
			final EntityPersister persister,
			final boolean disableVersionIncrement,
			final PersistenceContext persistenceContext) {
		super( status, loadedState, rowId, id, version, lockMode, existsInDatabase, persister,
				disableVersionIncrement, persistenceContext
		);
	}
