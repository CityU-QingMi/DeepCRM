	private void dereferenceEntity(String propertyName, EntityType propertyType, QueryTranslatorImpl q)
			throws QueryException {
		//NOTE: we avoid joining to the next table if the named property is just the foreign key value

		//if its "id"
		boolean isIdShortcut = EntityPersister.ENTITY_ID.equals( propertyName )
				&& propertyType.isReferenceToPrimaryKey();

		//or its the id property name
		final String idPropertyName;
		try {
			idPropertyName = propertyType.getIdentifierOrUniqueKeyPropertyName( q.getFactory() );
		}
		catch ( MappingException me ) {
			throw new QueryException( me );
		}
		boolean isNamedIdPropertyShortcut = idPropertyName != null
				&& idPropertyName.equals( propertyName )
				&& propertyType.isReferenceToPrimaryKey();


		if ( isIdShortcut || isNamedIdPropertyShortcut ) {
			// special shortcut for id properties, skip the join!
			// this must only occur at the _end_ of a path expression
			if ( componentPath.length() > 0 ) {
				componentPath.append( '.' );
			}
			componentPath.append( propertyName );
		}
		else {
			String entityClass = propertyType.getAssociatedEntityName();
			String name = q.createNameFor( entityClass );
			q.addType( name, entityClass );
			addJoin( name, propertyType );
			if ( propertyType.isOneToOne() ) {
				oneToOneOwnerName = currentName;
			}
			ownerAssociationType = propertyType;
			currentName = name;
			currentProperty = propertyName;
			q.addPathAliasAndJoin( path.substring( 0, path.toString().lastIndexOf( '.' ) ), name, joinSequence.copy() );
			componentPath.setLength( 0 );
			currentPropertyMapping = q.getEntityPersister( entityClass );
		}
	}
