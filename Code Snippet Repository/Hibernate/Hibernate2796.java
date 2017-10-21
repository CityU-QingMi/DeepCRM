	private void prepareForIndex(QueryTranslatorImpl q) throws QueryException {

		QueryableCollection collPersister = q.getCollectionPersister( collectionRole );

		if ( !collPersister.hasIndex() ) {
			throw new QueryException( "unindexed collection before []: " + path );
		}

		String[] indexCols = collPersister.getIndexColumnNames();
		if ( indexCols.length != 1 ) {
			throw new QueryException( "composite-index appears in []: " + path );
		}
		//String[] keyCols = collPersister.getKeyColumnNames();

		JoinSequence fromJoins = new JoinSequence( q.getFactory() )
				.setUseThetaStyle( useThetaStyleJoin )
				.setRoot( collPersister, collectionName )
				.setNext( joinSequence.copy() );

		if ( !continuation ) {
			addJoin( collectionName, collPersister.getCollectionType() );
		}

		joinSequence.addCondition( collectionName + '.' + indexCols[0] + " = " ); //TODO: get SQL rendering out of here

		CollectionElement elem = new CollectionElement();
		elem.elementColumns = collPersister.getElementColumnNames(collectionName);
		elem.elementType = collPersister.getElementType();
		elem.isOneToMany = collPersister.isOneToMany();
		elem.alias = collectionName;
		elem.joinSequence = joinSequence;
		collectionElements.addLast( elem );
		setExpectingCollectionIndex();

		q.addCollection( collectionName, collectionRole );
		q.addFromJoinOnly( collectionName, fromJoins );
	}
