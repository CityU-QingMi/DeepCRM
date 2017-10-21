	private EntityJoinFromElement createEntityJoin(
			EntityPersister entityPersister,
			AST aliasNode,
			int joinType,
			AST propertyFetch,
			AST with) throws SemanticException {
		final String alias = aliasNode == null ? null : aliasNode.getText();
		LOG.debugf( "Creating entity-join FromElement [%s -> %s]", alias, entityPersister.getEntityName() );
		EntityJoinFromElement join = new EntityJoinFromElement(
				this,
				getCurrentFromClause(),
				entityPersister,
				JoinProcessor.toHibernateJoinType( joinType ),
				propertyFetch != null,
				alias
		);

		if ( with != null ) {
			handleWithFragment( join, with );
		}

		return join;
	}
