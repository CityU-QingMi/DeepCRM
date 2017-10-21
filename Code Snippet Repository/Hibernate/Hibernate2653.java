	public void initializeEntity(
			FromClause fromClause,
			String className,
			EntityPersister persister,
			EntityType type,
			String classAlias,
			String tableAlias) {
		doInitialize( fromClause, tableAlias, className, classAlias, persister, type );
		this.sequence = fromClause.nextFromElementCounter();
		initialized = true;
	}
