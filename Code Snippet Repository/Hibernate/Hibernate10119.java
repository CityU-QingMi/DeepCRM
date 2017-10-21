	@Override
	public AuditWorkUnit merge(ModWorkUnit second) {
		// In case of multiple subsequent flushes within single transaction, modification flags need to be
		// recalculated against initial and final state of the given entity.
		return new ModWorkUnit(
				second.sessionImplementor,
				second.getEntityName(),
				second.enversService,
				second.id,
				second.entityPersister,
				second.newState,
				this.oldState
		);
	}
