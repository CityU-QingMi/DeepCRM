	private void createIndexBackRef(
			MappingDocument mappingDocument,
			IndexedPluralAttributeSource pluralAttributeSource,
			IndexedCollection collectionBinding) {
		if ( collectionBinding.isOneToMany()
				&& !collectionBinding.getKey().isNullable()
				&& !collectionBinding.isInverse() ) {
			final String entityName = ( (OneToMany) collectionBinding.getElement() ).getReferencedEntityName();
			final PersistentClass referenced = mappingDocument.getMetadataCollector().getEntityBinding( entityName );
			final IndexBackref ib = new IndexBackref();
			ib.setName( '_' + collectionBinding.getOwnerEntityName() + "." + pluralAttributeSource.getName() + "IndexBackref" );
			ib.setUpdateable( false );
			ib.setSelectable( false );
			ib.setCollectionRole( collectionBinding.getRole() );
			ib.setEntityName( collectionBinding.getOwner().getEntityName() );
			ib.setValue( collectionBinding.getIndex() );
			referenced.addProperty( ib );
		}
	}
