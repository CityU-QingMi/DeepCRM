	@Override
	public void startingCollectionIndex(CollectionIndexDefinition indexDefinition) {
		final Type indexType = indexDefinition.getType();
		log.tracef(
				"%s Starting collection index graph : %s",
				StringHelper.repeat( ">>", fetchSourceStack.size() ),
				indexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
		);

		final CollectionReference collectionReference = currentCollection();
		final CollectionFetchableIndex indexGraph = collectionReference.getIndexGraph();

		if ( indexType.isEntityType() || indexType.isComponentType() ) {
			if ( indexGraph == null ) {
				throw new WalkingException(
						"CollectionReference did not return an expected index graph : " +
								indexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				);
			}
			if ( !indexType.isAnyType() ) {
				pushToStack( (ExpandingFetchSource) indexGraph );
			}
		}
		else {
			if ( indexGraph != null ) {
				throw new WalkingException(
						"CollectionReference returned an unexpected index graph : " +
								indexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				);
			}
		}
	}
