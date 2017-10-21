	private LoadEvent(
			Serializable entityId,
			String entityClassName,
			Object instanceToLoad,
			LockMode lockMode,
			boolean isAssociationFetch,
			EventSource source) {
		this( entityId, entityClassName, instanceToLoad,
				lockMode == DEFAULT_LOCK_MODE ? DEFAULT_LOCK_OPTIONS : new LockOptions().setLockMode( lockMode ),
				isAssociationFetch, source );
	}
