	private void handleElements(FromReferenceNode collectionNode, String propertyName) {
		FromElement collectionFromElement = collectionNode.getFromElement();
		QueryableCollection queryableCollection = collectionFromElement.getQueryableCollection();

		String path = collectionNode.getPath() + "[]." + propertyName;
		LOG.debugf( "Creating elements for %s", path );

		fromElement = collectionFromElement;
		if ( !collectionFromElement.isCollectionOfValuesOrComponents() ) {
			getWalker().addQuerySpaces( queryableCollection.getElementPersister().getQuerySpaces() );
		}

		setDataType( queryableCollection.getElementType() );
		selectColumns = collectionFromElement.toColumns( fromElement.getTableAlias(), propertyName, inSelect );
	}
