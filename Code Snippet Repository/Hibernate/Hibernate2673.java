	private FromElement createManyToMany(
			String role,
			String associatedEntityName,
			String roleAlias,
			Queryable entityPersister,
			EntityType type,
			JoinType joinType) throws SemanticException {
		FromElement elem;
		SessionFactoryHelper sfh = fromClause.getSessionFactoryHelper();
		if ( inElementsFunction /*implied*/ ) {
			// For implied many-to-many, just add the end join.
			JoinSequence joinSequence = createJoinSequence( roleAlias, joinType );
			elem = createJoin( associatedEntityName, roleAlias, joinSequence, type, true );
		}
		else {
			// For an explicit many-to-many relationship, add a second join from the intermediate
			// (many-to-many) table to the destination table.  Also, make sure that the from element's
			// idea of the destination is the destination table.
			String tableAlias = fromClause.getAliasGenerator().createName( entityPersister.getEntityName() );
			String[] secondJoinColumns = sfh.getCollectionElementColumns( role, roleAlias );
			// Add the second join, the one that ends in the destination table.
			JoinSequence joinSequence = createJoinSequence( roleAlias, joinType );
			joinSequence.addJoin(
					sfh.getElementAssociationType( collectionType ),
					tableAlias,
					joinType,
					secondJoinColumns
			);
			elem = createJoin( associatedEntityName, tableAlias, joinSequence, type, false );
			elem.setUseFromFragment( true );
		}
		return elem;
	}
