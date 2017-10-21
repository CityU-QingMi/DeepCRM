	@SuppressWarnings({""})
	private String searchMappedByKey(PersistentClass referencedClass, Collection collectionValue) {
		final Iterator<Value> assocIdClassProps = referencedClass.getKeyClosureIterator();
		while ( assocIdClassProps.hasNext() ) {
			final Value value = assocIdClassProps.next();
			// make sure its a 'Component' because IdClass is registered as this type.
			if ( value instanceof Component ) {
				final Component component = (Component) value;
				final Iterator<Property> componentPropertyIterator = component.getPropertyIterator();
				while ( componentPropertyIterator.hasNext() ) {
					final Property property = componentPropertyIterator.next();
					final Iterator<Selectable> propertySelectables = property.getValue().getColumnIterator();
					final Iterator<Selectable> collectionSelectables = collectionValue.getKey().getColumnIterator();
					if ( Tools.iteratorsContentEqual( propertySelectables, collectionSelectables ) ) {
						return property.getName();
					}
				}
			}
		}
		return null;
	}
