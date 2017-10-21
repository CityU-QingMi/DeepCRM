	@Override
	public EntityEntry createEntityEntry(
			Status status,
			Object[] loadedState,
			Object rowId,
			Serializable id,
			Object version,
			LockMode lockMode,
			boolean existsInDatabase,
			EntityPersister persister,
			boolean disableVersionIncrement,
			PersistenceContext persistenceContext) {
		return new MutableEntityEntry(
				status,
				loadedState,
				rowId,
				id,
				version,
				lockMode,
				existsInDatabase,
				persister,
				disableVersionIncrement,
				persistenceContext
		);
	}
