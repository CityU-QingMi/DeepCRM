	PropertyMapping getPropertyMapping(String propertyName) {
		checkInitialized();
		if ( queryableCollection == null ) {        // Not a collection?
			return (PropertyMapping) persister;    // Return the entity property mapping.
		}

		// indexed, many-to-many collections must be treated specially here if the property to
		// be mapped touches on the index as we must adjust the alias to use the alias from
		// the association table (which i different than the one passed in
		if ( queryableCollection.isManyToMany()
				&& queryableCollection.hasIndex()
				&& SPECIAL_MANY2MANY_TREATMENT_FUNCTION_NAMES.contains( propertyName ) ) {
			return new SpecialManyToManyCollectionPropertyMapping();
		}

		// If the property is a special collection property name, return a CollectionPropertyMapping.
		if ( CollectionProperties.isCollectionProperty( propertyName ) ) {
			if ( collectionPropertyMapping == null ) {
				// lets additionally make sure that the property name is not also the name
				// of a property on the element, assuming that the element is an entity.
				// todo : also consider composites?
				if ( persister != null ) {
					try {
						if ( persister.getPropertyType( propertyName ) != null ) {
							return (PropertyMapping) persister;
						}
					}
					catch (QueryException ignore) {
					}
				}
				collectionPropertyMapping = new CollectionPropertyMapping( queryableCollection );
			}
			return collectionPropertyMapping;
		}

		if ( queryableCollection.getElementType().isAnyType() ) {
			// collection of <many-to-any/> mappings...
			// used to circumvent the component-collection check below...
			return queryableCollection;

		}

		if ( queryableCollection.getElementType().isComponentType() ) {
			// Collection of components.
			if ( propertyName.equals( EntityPersister.ENTITY_ID ) ) {
				return (PropertyMapping) queryableCollection.getOwnerEntityPersister();
			}
		}
		return queryableCollection;
	}
