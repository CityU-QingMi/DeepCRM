	@Override
	public AuditWorkUnit merge(ModWorkUnit second) {
		return new AddWorkUnit(
				sessionImplementor,
				entityName,
				enversService,
				id,
				mergeModifiedFlags( data, second.getData() )
		);
	}
