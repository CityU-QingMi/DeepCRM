	private FromElement createJoin(
			String entityClass,
			String tableAlias,
			JoinSequence joinSequence,
			EntityType type,
			boolean manyToMany) throws SemanticException {
		//  origin, path, implied, columns, classAlias,
		EntityPersister entityPersister = fromClause.getSessionFactoryHelper().requireClassPersister( entityClass );
		FromElement destination = createAndAddFromElement(
				entityClass,
				classAlias,
				entityPersister,
				type,
				tableAlias
		);
		return initializeJoin( path, destination, joinSequence, getColumns(), origin, manyToMany );
	}
