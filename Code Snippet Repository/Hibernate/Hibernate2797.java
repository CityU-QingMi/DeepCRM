	public String addFromCollection(QueryTranslatorImpl q) throws QueryException {
		Type collectionElementType = getPropertyType();

		if ( collectionElementType == null ) {
			throw new QueryException( "must specify 'elements' for collection valued property in from clause: " + path );
		}

		if ( collectionElementType.isEntityType() ) {
			// an association
			QueryableCollection collectionPersister = q.getCollectionPersister( collectionRole );
			Queryable entityPersister = ( Queryable ) collectionPersister.getElementPersister();
			String clazz = entityPersister.getEntityName();

			final String elementName;
			if ( collectionPersister.isOneToMany() ) {
				elementName = collectionName;
				//allow index() function:
				q.decoratePropertyMapping( elementName, collectionPersister );
			}
			else { //many-to-many
				q.addCollection( collectionName, collectionRole );
				elementName = q.createNameFor( clazz );
				addJoin( elementName, ( AssociationType ) collectionElementType );
			}
			q.addFrom( elementName, clazz, joinSequence );
			currentPropertyMapping = new CollectionPropertyMapping( collectionPersister );
			return elementName;
		}
		else {
			// collections of values
			q.addFromCollection( collectionName, collectionRole, joinSequence );
			return collectionName;
		}

	}
