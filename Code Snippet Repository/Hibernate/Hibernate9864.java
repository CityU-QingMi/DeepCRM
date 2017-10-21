	@SuppressWarnings({""})
	private String searchMappedBy(PersistentClass referencedClass, Table collectionTable) {
		final Iterator<Property> properties = referencedClass.getPropertyIterator();
		while ( properties.hasNext() ) {
			final Property property = properties.next();
			if ( property.getValue() instanceof Collection ) {
				// The equality is intentional. We want to find a collection property with the same collection table.
				//noinspection ObjectEquality
				if ( ( (Collection) property.getValue() ).getCollectionTable() == collectionTable ) {
					return property.getName();
				}
			}
		}
		return null;
	}
