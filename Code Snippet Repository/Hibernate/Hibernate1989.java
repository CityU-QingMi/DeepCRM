	private String checkSubElementsNullability(Type propertyType, Object value) throws HibernateException {
		if ( propertyType.isComponentType() ) {
			return checkComponentNullability( value, (CompositeType) propertyType );
		}

		if ( propertyType.isCollectionType() ) {
			// persistent collections may have components
			final CollectionType collectionType = (CollectionType) propertyType;
			final Type collectionElementType = collectionType.getElementType( session.getFactory() );

			if ( collectionElementType.isComponentType() ) {
				// check for all components values in the collection
				final CompositeType componentType = (CompositeType) collectionElementType;
				final Iterator itr = CascadingActions.getLoadedElementsIterator( session, collectionType, value );
				while ( itr.hasNext() ) {
					final Object compositeElement = itr.next();
					if ( compositeElement != null ) {
						return checkComponentNullability( compositeElement, componentType );
					}
				}
			}
		}

		return null;
	}
