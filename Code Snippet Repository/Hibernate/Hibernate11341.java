	public static Set<String> extractModProperties(PersistentClass persistentClass, String suffix) {
		final Set<String> result = new HashSet<String>();
		final Iterator iterator = persistentClass.getPropertyIterator();
		while ( iterator.hasNext() ) {
			final Property property = (Property) iterator.next();
			final String propertyName = property.getName();
			if ( propertyName.endsWith( suffix ) ) {
				result.add( propertyName );
			}
		}
		return result;
	}
