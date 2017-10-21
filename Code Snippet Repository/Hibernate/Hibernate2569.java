	@Override
	protected AST createFromFilterElement(AST filterEntity, AST alias) throws SemanticException {
		FromElement fromElement = currentFromClause.addFromElement( filterEntity.getText(), alias );
		FromClause fromClause = fromElement.getFromClause();
		QueryableCollection persister = sessionFactoryHelper.getCollectionPersister( collectionFilterRole );
		// Get the names of the columns used to link between the collection
		// owner and the collection elements.
		String[] keyColumnNames = persister.getKeyColumnNames();
		String fkTableAlias = persister.isOneToMany()
				? fromElement.getTableAlias()
				: fromClause.getAliasGenerator().createName( collectionFilterRole );
		JoinSequence join = sessionFactoryHelper.createJoinSequence();
		join.setRoot( persister, fkTableAlias );
		if ( !persister.isOneToMany() ) {
			join.addJoin(
					(AssociationType) persister.getElementType(),
					fromElement.getTableAlias(),
					JoinType.INNER_JOIN,
					persister.getElementColumnNames( fkTableAlias )
			);
		}
		join.addCondition( fkTableAlias, keyColumnNames, " = ?" );
		fromElement.setJoinSequence( join );
		fromElement.setFilter( true );
		LOG.debug( "createFromFilterElement() : processed filter FROM element." );
		return fromElement;
	}
