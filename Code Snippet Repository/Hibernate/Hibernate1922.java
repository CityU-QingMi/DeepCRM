	@SuppressWarnings( {""})
	protected AbstractEntityEntry(
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
		this.persister = ( factory == null ? null : factory.getEntityPersister( entityName ) );
		this.id = id;
		setCompressedValue( EnumState.STATUS, status );
		setCompressedValue( EnumState.PREVIOUS_STATUS, previousStatus );
		this.loadedState = loadedState;
		setDeletedState( deletedState );
		this.version = version;
		setCompressedValue( EnumState.LOCK_MODE, lockMode );
		setCompressedValue( BooleanState.EXISTS_IN_DATABASE, existsInDatabase );
		setCompressedValue( BooleanState.IS_BEING_REPLICATED, isBeingReplicated );
		this.rowId = null; // this is equivalent to the old behavior...
		this.persistenceContext = persistenceContext;
	}
