	@Override
	public void startingCollectionElements(CollectionElementDefinition elementDefinition) {
		final Type elementType = elementDefinition.getType();
		log.tracef(
				"%s Starting collection element graph : %s",
				StringHelper.repeat( ">>", fetchSourceStack.size() ),
				elementDefinition.getCollectionDefinition().getCollectionPersister().getRole()
		);

		final CollectionReference collectionReference = currentCollection();
		final CollectionFetchableElement elementGraph = collectionReference.getElementGraph();

		if ( elementType.isAssociationType() || elementType.isComponentType() ) {
			if ( elementGraph == null ) {
				throw new IllegalStateException(
						"CollectionReference did not return an expected element graph : " +
								elementDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				);
			}
			if ( !elementType.isAnyType() ) {
				pushToStack( (ExpandingFetchSource) elementGraph );
			}
		}
		else {
			if ( elementGraph != null ) {
				throw new IllegalStateException(
						"CollectionReference returned an unexpected element graph : " +
								elementDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				);
			}
		}
	}
