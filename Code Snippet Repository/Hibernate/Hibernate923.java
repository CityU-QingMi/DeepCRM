		protected void bindCollectionIdentifier() {
			final CollectionIdSource idSource = getPluralAttributeSource().getCollectionIdSource();
			if ( idSource != null ) {
				final IdentifierCollection idBagBinding = (IdentifierCollection) getCollectionBinding();
				final SimpleValue idBinding = new SimpleValue(
						mappingDocument.getMetadataCollector(),
						idBagBinding.getCollectionTable()
				);

				bindSimpleValueType(
						mappingDocument,
						idSource.getTypeInformation(),
						idBinding
				);

				relationalObjectBinder.bindColumn(
						mappingDocument,
						idSource.getColumnSource(),
						idBinding,
						false,
						new RelationalObjectBinder.ColumnNamingDelegate() {
							@Override
							public Identifier determineImplicitName(LocalMetadataBuildingContext context) {
								return database.toIdentifier( IdentifierCollection.DEFAULT_IDENTIFIER_COLUMN_NAME );
							}
						}
				);

				idBagBinding.setIdentifier( idBinding );

				makeIdentifier(
						mappingDocument,
						new IdentifierGeneratorDefinition( idSource.getGeneratorName(), idSource.getParameters() ),
						null,
						idBinding
				);
			}
		}
