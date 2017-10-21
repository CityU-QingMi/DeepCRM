	private void initializeAndAddFromElement(
			FromElement element,
			String className,
			String classAlias,
			EntityPersister entityPersister,
			EntityType type,
			String tableAlias) {
		if ( tableAlias == null ) {
			AliasGenerator aliasGenerator = fromClause.getAliasGenerator();
			tableAlias = aliasGenerator.createName( entityPersister.getEntityName() );
		}
		element.initializeEntity( fromClause, className, entityPersister, type, classAlias, tableAlias );
	}
