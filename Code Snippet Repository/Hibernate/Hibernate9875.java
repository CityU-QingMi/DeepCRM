	@SuppressWarnings({""})
	private String searchMappedBy(PersistentClass referencedClass, Collection collectionValue) {
		final Iterator<Property> assocClassProps = referencedClass.getPropertyIterator();
		while ( assocClassProps.hasNext() ) {
			final Property property = assocClassProps.next();

			if ( Tools.iteratorsContentEqual(
					property.getValue().getColumnIterator(),
					collectionValue.getKey().getColumnIterator()
			) ) {
				return property.getName();
			}
		}
		// HHH-7625
		// Support ToOne relations with mappedBy that point to an @IdClass key property.
		return searchMappedByKey( referencedClass, collectionValue );
	}
