	public String[] toAliasedColumns(String alias, String propertyName) {
		final QueryableCollection queryableCollection = (QueryableCollection) persister;
		if ( propertyName.equals( CollectionPropertyNames.COLLECTION_ELEMENTS ) ) {
			return queryableCollection.getElementColumnNames( alias );
		}
		else if ( propertyName.equals( CollectionPropertyNames.COLLECTION_INDICES ) ) {
			return queryableCollection.getIndexColumnNames( alias );
		}
		else {
			throw new IllegalArgumentException(
					String.format(
							"Collection propertyName must be either %s or %s; instead it was %s.",
							CollectionPropertyNames.COLLECTION_ELEMENTS,
							CollectionPropertyNames.COLLECTION_INDICES,
							propertyName
					)
			);
		}
	}
