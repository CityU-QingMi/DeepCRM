	private LoadEvent(
			Serializable entityId,
			String entityClassName,
			Object instanceToLoad,
			LockOptions lockOptions,
			boolean isAssociationFetch,
			EventSource source) {

		super(source);

		if ( entityId == null ) {
			throw new IllegalArgumentException("id to load is required for loading");
		}

		if ( lockOptions.getLockMode() == LockMode.WRITE ) {
			throw new IllegalArgumentException("Invalid lock mode for loading");
		}
		else if ( lockOptions.getLockMode() == null ) {
			lockOptions.setLockMode(DEFAULT_LOCK_MODE);
		}

		this.entityId = entityId;
		this.entityClassName = entityClassName;
		this.instanceToLoad = instanceToLoad;
		this.lockOptions = lockOptions;
		this.isAssociationFetch = isAssociationFetch;
		this.postLoadEvent = new PostLoadEvent( source );
	}
