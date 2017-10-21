	public void bindListOrArrayIndex(
			MappingDocument mappingDocument,
			final IndexedPluralAttributeSource attributeSource,
			org.hibernate.mapping.List collectionBinding) {
		final PluralAttributeSequentialIndexSource indexSource =
				(PluralAttributeSequentialIndexSource) attributeSource.getIndexSource();

		final SimpleValue indexBinding = new SimpleValue(
				mappingDocument.getMetadataCollector(),
				collectionBinding.getCollectionTable()
		);

		bindSimpleValueType(
				mappingDocument,
				indexSource.getTypeInformation(),
				indexBinding
		);

		relationalObjectBinder.bindColumnsAndFormulas(
				mappingDocument,
				indexSource.getRelationalValueSources(),
				indexBinding,
				attributeSource.getElementSource() instanceof PluralAttributeElementSourceOneToMany,
				new RelationalObjectBinder.ColumnNamingDelegate() {
					@Override
					public Identifier determineImplicitName(final LocalMetadataBuildingContext context) {
						return context.getBuildingOptions().getImplicitNamingStrategy().determineListIndexColumnName(
								new ImplicitIndexColumnNameSource() {
									@Override
									public AttributePath getPluralAttributePath() {
										return attributeSource.getAttributePath();
									}

									@Override
									public MetadataBuildingContext getBuildingContext() {
										return context;
									}
								}
						);
					}
				}
		);

		collectionBinding.setIndex( indexBinding );
		collectionBinding.setBaseIndex( indexSource.getBase() );
	}
