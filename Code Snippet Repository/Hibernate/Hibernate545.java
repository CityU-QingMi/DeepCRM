	public EntityIdentityInsertAction(
			Object[] state,
			Object instance,
			EntityPersister persister,
			boolean isVersionIncrementDisabled,
			SharedSessionContractImplementor session,
			boolean isDelayed) {
		super(
				( isDelayed ? generateDelayedPostInsertIdentifier() : null ),
				state,
				instance,
				isVersionIncrementDisabled,
				persister,
				session
		);
		this.isDelayed = isDelayed;
		this.delayedEntityKey = isDelayed ? generateDelayedEntityKey() : null;
	}
