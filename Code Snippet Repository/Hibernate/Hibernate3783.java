	public CollectionReferenceAliases generateCollectionReferenceAliases(
			String collectionQuerySpaceUid,
			CollectionPersister persister,
			String elementQuerySpaceUid) {
		if ( persister.getElementType().isEntityType() && elementQuerySpaceUid == null ) {
			throw new IllegalArgumentException(
					"elementQuerySpaceUid must be non-null for one-to-many or many-to-many associations."
			);
		}

		final String manyToManyTableAlias;
		final String tableAlias;
		if ( persister.isManyToMany() ) {
			manyToManyTableAlias = createTableAlias( persister.getRole() );
			tableAlias = createTableAlias( persister.getElementDefinition().toEntityDefinition().getEntityPersister() );
		}
		else {
			manyToManyTableAlias = null;
			tableAlias = createTableAlias( persister.getRole() );
		}

		final CollectionReferenceAliases collectionAliases = new CollectionReferenceAliasesImpl(
				tableAlias,
				manyToManyTableAlias,
				createCollectionAliases( persister ),
				createCollectionElementAliases( persister, tableAlias, elementQuerySpaceUid )
		);

		registerQuerySpaceAliases( collectionQuerySpaceUid, collectionAliases );
		return collectionAliases;
	}
