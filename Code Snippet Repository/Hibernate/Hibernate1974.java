	@SuppressWarnings( {""})
	private MutableEntityEntry(
			final SessionFactoryImplementor factory,
			final String entityName,
			final Serializable id,
			final Status status,
			final Status previousStatus,
			final Object[] loadedState,
			final Object[] deletedState,
			final Object version,
			final LockMode lockMode,
			final boolean existsInDatabase,
			final boolean isBeingReplicated,
			final PersistenceContext persistenceContext) {
		super( factory, entityName, id, status, previousStatus, loadedState, deletedState,
				version, lockMode, existsInDatabase, isBeingReplicated, persistenceContext
		);
	}
