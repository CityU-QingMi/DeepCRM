	private boolean parseElementCollection(ElementCollection collection) {
		String[] types;
		XmlMetaCollection metaCollection;
		ElementKind elementKind = getElementKind( collection.getAccess() );
		String explicitTargetClass = determineExplicitTargetEntity( collection.getTargetClass() );
		String explicitMapKey = determineExplicitMapKeyClass( collection.getMapKeyClass() );
		try {
			types = getCollectionTypes(
					collection.getName(), explicitTargetClass, explicitMapKey, elementKind
			);
		}
		catch ( MetaModelGenerationException e ) {
			logMetaModelException( collection.getName(), e );
			return true;
		}
		if ( types != null ) {
			if ( types[2] == null ) {
				metaCollection = new XmlMetaCollection( this, collection.getName(), types[0], types[1] );
			}
			else {
				metaCollection = new XmlMetaMap( this, collection.getName(), types[0], types[1], types[2] );
			}
			members.add( metaCollection );
		}
		return false;
	}
