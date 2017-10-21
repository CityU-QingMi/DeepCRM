	public CollectionRemoveAction(
				final Object affectedOwner,
				final CollectionPersister persister,
				final Serializable id,
				final boolean emptySnapshot,
				final SharedSessionContractImplementor session) {
		super( persister, null, id, session );
		if ( affectedOwner == null ) {
			throw new AssertionFailure("affectedOwner == null");
		}
		this.emptySnapshot = emptySnapshot;
		this.affectedOwner = affectedOwner;
	}
