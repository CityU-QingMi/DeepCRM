	@SuppressWarnings("")
	public boolean hasProperty(String name) {
		final Iterator itr = getDeclaredPropertyIterator();
		while ( itr.hasNext() ) {
			final Property property = (Property) itr.next();
			if ( property.getName().equals( name ) ) {
				return true;
			}
		}

		return false;
	}
