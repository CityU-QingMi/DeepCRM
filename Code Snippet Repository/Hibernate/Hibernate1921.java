	public AbstractEntityEntry(
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
		setCompressedValue( EnumState.STATUS, status );
		// not useful strictly speaking but more explicit
		setCompressedValue( EnumState.PREVIOUS_STATUS, null );
		// only retain loaded state if the status is not Status.READ_ONLY
		if ( status != Status.READ_ONLY ) {
			this.loadedState = loadedState;
		}
		this.id=id;
		this.rowId=rowId;
		setCompressedValue( BooleanState.EXISTS_IN_DATABASE, existsInDatabase );
		this.version=version;
		setCompressedValue( EnumState.LOCK_MODE, lockMode );
		setCompressedValue( BooleanState.IS_BEING_REPLICATED, disableVersionIncrement );
		this.persister=persister;
		this.persistenceContext = persistenceContext;
	}
