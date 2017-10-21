	private void addFromAssociation(final String elementName, final String collectionRole)
			throws QueryException {
		//q.addCollection(collectionName, collectionRole);
		QueryableCollection persister = getCollectionPersister( collectionRole );
		Type collectionElementType = persister.getElementType();
		if ( !collectionElementType.isEntityType() ) {
			throw new QueryException( "collection of values in filter: " + elementName );
		}

		String[] keyColumnNames = persister.getKeyColumnNames();
		//if (keyColumnNames.length!=1) throw new QueryException("composite-key collection in filter: " + collectionRole);

		String collectionName;
		JoinSequence join = new JoinSequence( getFactory() );
		collectionName = persister.isOneToMany() ?
				elementName :
				createNameForCollection( collectionRole );
		join.setRoot( persister, collectionName );
		if ( !persister.isOneToMany() ) {
			//many-to-many
			addCollection( collectionName, collectionRole );
			try {
				join.addJoin(
						(AssociationType) persister.getElementType(),
						elementName,
						JoinType.INNER_JOIN,
						persister.getElementColumnNames( collectionName )
				);
			}
			catch (MappingException me) {
				throw new QueryException( me );
			}
		}
		join.addCondition( collectionName, keyColumnNames, " = ?" );
		//if ( persister.hasWhere() ) join.addCondition( persister.getSQLWhereString(collectionName) );
		EntityType elemType = (EntityType) collectionElementType;
		addFrom( elementName, elemType.getAssociatedEntityName(), join );

	}
