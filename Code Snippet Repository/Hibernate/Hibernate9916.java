	private void readPersistentPropertiesAccess() {
		final Iterator<Property> propertyIter = persistentPropertiesSource.getPropertyIterator();
		while ( propertyIter.hasNext() ) {
			final Property property = propertyIter.next();
			addPersistentProperty( property );
			// See HHH-6636
			if ( "embedded".equals( property.getPropertyAccessorName() ) && !PropertyPath.IDENTIFIER_MAPPER_PROPERTY.equals( property.getName() ) ) {
				createPropertiesGroupMapping( property );
			}
		}
	}
