	private FromElement createEntityAssociation(
			String role,
			String roleAlias,
			JoinType joinType) throws SemanticException {
		FromElement elem;
		Queryable entityPersister = (Queryable) queryableCollection.getElementPersister();
		String associatedEntityName = entityPersister.getEntityName();
		// Get the class name of the associated entity.
		if ( queryableCollection.isOneToMany() ) {
			LOG.debugf(
					"createEntityAssociation() : One to many - path = %s role = %s associatedEntityName = %s",
					path,
					role,
					associatedEntityName
			);
			JoinSequence joinSequence = createJoinSequence( roleAlias, joinType );

			elem = createJoin(
					associatedEntityName,
					roleAlias,
					joinSequence,
					(EntityType) queryableCollection.getElementType(),
					false
			);
		}
		else {
			LOG.debugf(
					"createManyToMany() : path = %s role = %s associatedEntityName = %s",
					path,
					role,
					associatedEntityName
			);
			elem = createManyToMany(
					role, associatedEntityName,
					roleAlias, entityPersister, (EntityType) queryableCollection.getElementType(), joinType
			);
			fromClause.getWalker().addQuerySpaces( queryableCollection.getCollectionSpaces() );
		}
		elem.setCollectionTableAlias( roleAlias );
		return elem;
	}
