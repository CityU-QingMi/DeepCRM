	private boolean parseOneToMany(OneToMany oneToMany) {
		String[] types;
		XmlMetaCollection metaCollection;
		ElementKind elementKind = getElementKind( oneToMany.getAccess() );
		String explicitTargetClass = determineExplicitTargetEntity( oneToMany.getTargetEntity() );
		String explicitMapKey = determineExplicitMapKeyClass( oneToMany.getMapKeyClass() );
		try {
			types = getCollectionTypes( oneToMany.getName(), explicitTargetClass, explicitMapKey, elementKind );
		}
		catch ( MetaModelGenerationException e ) {
			logMetaModelException( oneToMany.getName(), e );
			return true;
		}
		if ( types != null ) {
			if ( types[2] == null ) {
				metaCollection = new XmlMetaCollection( this, oneToMany.getName(), types[0], types[1] );
			}
			else {
				metaCollection = new XmlMetaMap( this, oneToMany.getName(), types[0], types[1], types[2] );
			}
			members.add( metaCollection );
		}
		return false;
	}
