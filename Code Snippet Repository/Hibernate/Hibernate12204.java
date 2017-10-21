	private boolean parseManyToMany(ManyToMany manyToMany) {
		String[] types;
		XmlMetaCollection metaCollection;
		ElementKind elementKind = getElementKind( manyToMany.getAccess() );
		String explicitTargetClass = determineExplicitTargetEntity( manyToMany.getTargetEntity() );
		String explicitMapKey = determineExplicitMapKeyClass( manyToMany.getMapKeyClass() );
		try {
			types = getCollectionTypes(
					manyToMany.getName(), explicitTargetClass, explicitMapKey, elementKind
			);
		}
		catch ( MetaModelGenerationException e ) {
			logMetaModelException( manyToMany.getName(), e );
			return true;
		}
		if ( types != null ) {
			if ( types[2] == null ) {
				metaCollection = new XmlMetaCollection( this, manyToMany.getName(), types[0], types[1] );
			}
			else {
				metaCollection = new XmlMetaMap( this, manyToMany.getName(), types[0], types[1], types[2] );
			}
			members.add( metaCollection );
		}
		return false;
	}
