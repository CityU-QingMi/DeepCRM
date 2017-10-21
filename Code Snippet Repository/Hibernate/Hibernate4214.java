	private void visitCollectionElements(CollectionDefinition collectionDefinition) {
		final CollectionElementDefinition elementDefinition = collectionDefinition.getElementDefinition();
		strategy.startingCollectionElements( elementDefinition );

		final Type collectionElementType = elementDefinition.getType();
		if ( collectionElementType.isAnyType() ) {
			visitAnyDefinition( elementDefinition.toAnyMappingDefinition() );
		}
		else if ( collectionElementType.isComponentType() ) {
			visitCompositeDefinition( elementDefinition.toCompositeElementDefinition() );
		}
		else if ( collectionElementType.isEntityType() ) {
			if ( ! collectionDefinition.getCollectionPersister().isOneToMany() ) {
				final QueryableCollection queryableCollection = (QueryableCollection) collectionDefinition.getCollectionPersister();
				addAssociationKey(
						new AssociationKey(
								queryableCollection.getTableName(),
								queryableCollection.getElementColumnNames()
						)
				);
			}
			visitEntityDefinition( elementDefinition.toEntityDefinition() );
		}

		strategy.finishingCollectionElements( elementDefinition );
	}
