	private void visitCollectionIndex(CollectionDefinition collectionDefinition) {
		final CollectionIndexDefinition collectionIndexDefinition = collectionDefinition.getIndexDefinition();
		if ( collectionIndexDefinition == null ) {
			return;
		}

		strategy.startingCollectionIndex( collectionIndexDefinition );

		log.debug( "Visiting index for collection :  " + currentPropertyPath.getFullPath() );
		currentPropertyPath = currentPropertyPath.append( "<index>" );

		try {
			final Type collectionIndexType = collectionIndexDefinition.getType();
			if ( collectionIndexType.isAnyType() ) {
				visitAnyDefinition( collectionIndexDefinition.toAnyMappingDefinition() );
			}
			else if ( collectionIndexType.isComponentType() ) {
				visitCompositeDefinition( collectionIndexDefinition.toCompositeDefinition() );
			}
			else if ( collectionIndexType.isAssociationType() ) {
				visitEntityDefinition( collectionIndexDefinition.toEntityDefinition() );
			}
		}
		finally {
			currentPropertyPath = currentPropertyPath.getParent();
		}

		strategy.finishingCollectionIndex( collectionIndexDefinition );
	}
