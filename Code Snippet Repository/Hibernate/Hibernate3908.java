	@SuppressWarnings("")
	public boolean hasProperty(String name) {
		final Property identifierProperty = getIdentifierProperty();
		if ( identifierProperty != null && identifierProperty.getName().equals( name ) ) {
			return true;
		}

		final Iterator itr = getPropertyClosureIterator();
		while ( itr.hasNext() ) {
			final Property property = (Property) itr.next();
			if ( property.getName().equals( name ) ) {
				return true;
			}
		}

		return false;
	}
