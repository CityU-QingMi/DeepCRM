	private void processJoinReturn(NativeSQLQueryJoinReturn fetchReturn) {
		String alias = fetchReturn.getAlias();
//		if ( alias2Persister.containsKey( alias ) || collectionAliases.contains( alias ) ) {
		if ( alias2Persister.containsKey( alias ) || alias2CollectionPersister.containsKey( alias ) ) {
			// already been processed...
			return;
		}

		String ownerAlias = fetchReturn.getOwnerAlias();

		// Make sure the owner alias is known...
		if ( !alias2Return.containsKey( ownerAlias ) ) {
			throw new HibernateException( "Owner alias [" + ownerAlias + "] is unknown for alias [" + alias + "]" );
		}

		// If this return's alias has not been processed yet, do so b4 further processing of this return
		if ( !alias2Persister.containsKey( ownerAlias ) ) {
			NativeSQLQueryNonScalarReturn ownerReturn = ( NativeSQLQueryNonScalarReturn ) alias2Return.get(ownerAlias);
			processReturn( ownerReturn );
		}

		SQLLoadable ownerPersister = ( SQLLoadable ) alias2Persister.get( ownerAlias );
		Type returnType = ownerPersister.getPropertyType( fetchReturn.getOwnerProperty() );

		if ( returnType.isCollectionType() ) {
			String role = ownerPersister.getEntityName() + '.' + fetchReturn.getOwnerProperty();
			addCollection( role, alias, fetchReturn.getPropertyResultsMap() );
//			collectionOwnerAliases.add( ownerAlias );
		}
		else if ( returnType.isEntityType() ) {
			EntityType eType = ( EntityType ) returnType;
			String returnEntityName = eType.getAssociatedEntityName();
			SQLLoadable persister = getSQLLoadable( returnEntityName );
			addPersister( alias, fetchReturn.getPropertyResultsMap(), persister );
		}

	}
