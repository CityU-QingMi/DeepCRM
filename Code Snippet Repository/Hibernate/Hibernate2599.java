	protected MapKeyEntityFromElement findOrAddMapKeyEntityFromElement(QueryableCollection collectionPersister) {
		if ( !collectionPersister.getIndexType().isEntityType() ) {
			return null;
		}


		for ( FromElement destination : getFromElement().getDestinations() ) {
			if ( destination instanceof MapKeyEntityFromElement ) {
				return (MapKeyEntityFromElement) destination;
			}
		}

		return MapKeyEntityFromElement.buildKeyJoin( getFromElement() );
	}
