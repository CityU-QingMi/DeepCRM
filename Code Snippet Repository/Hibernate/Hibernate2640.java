	public EntityJoinFromElement(
			HqlSqlWalker walker,
			FromClause fromClause,
			EntityPersister entityPersister,
			JoinType joinType,
			boolean fetchProperties,
			String alias) {
		initialize( walker );

		final String tableName = ( (Joinable) entityPersister ).getTableName();
		final String tableAlias = fromClause.getAliasGenerator().createName( entityPersister.getEntityName() );

		final EntityType entityType = (EntityType) ( (Queryable) entityPersister ).getType();

		initializeEntity(
				fromClause,
				entityPersister.getEntityName(),
				entityPersister,
				entityType,
				alias,
				tableAlias
		);

		EntityJoinJoinSequenceImpl joinSequence = new EntityJoinJoinSequenceImpl(
				getSessionFactoryHelper().getFactory(),
				entityType,
				tableName,
				tableAlias,
				joinType

		);
		setJoinSequence( joinSequence );

		setAllPropertyFetch( fetchProperties );

		// Add to the query spaces.
		fromClause.getWalker().addQuerySpaces( entityPersister.getQuerySpaces() );

		setType( HqlSqlTokenTypes.ENTITY_JOIN );
//		setType( HqlSqlTokenTypes.FROM_FRAGMENT );
		setText( tableName );
	}
