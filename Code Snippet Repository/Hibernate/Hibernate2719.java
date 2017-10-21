	@Override
	protected String[] resolveColumns(QueryableCollection collectionPersister) {
		this.mapKeyEntityFromElement = findOrAddMapKeyEntityFromElement( collectionPersister );
		if ( mapKeyEntityFromElement != null ) {
			setFromElement( mapKeyEntityFromElement );
		}

		final FromElement fromElement = getMapFromElement();
		return fromElement.toColumns(
				fromElement.getCollectionTableAlias(),
				"index", // the JPA KEY "qualifier" is the same concept as the HQL INDEX function/property
				getWalker().isInSelect()
		);
	}
