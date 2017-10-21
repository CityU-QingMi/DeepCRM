	private void bindEntityDiscriminator(
			MappingDocument sourceDocument,
			final EntityHierarchySourceImpl hierarchySource,
			RootClass rootEntityDescriptor) {
		final SimpleValue discriminatorValue = new SimpleValue(
				sourceDocument.getMetadataCollector(),
				rootEntityDescriptor.getTable()
		);
		rootEntityDescriptor.setDiscriminator( discriminatorValue );

		String typeName = hierarchySource.getDiscriminatorSource().getExplicitHibernateTypeName();
		if ( typeName == null ) {
			typeName = "string";
		}
		bindSimpleValueType(
				sourceDocument,
				new HibernateTypeSourceImpl( typeName ),
				discriminatorValue
		);

		relationalObjectBinder.bindColumnOrFormula(
				sourceDocument,
				hierarchySource.getDiscriminatorSource().getDiscriminatorRelationalValueSource(),
				discriminatorValue,
				false,
				new RelationalObjectBinder.ColumnNamingDelegate() {
					@Override
					public Identifier determineImplicitName(final LocalMetadataBuildingContext context) {
						return implicitNamingStrategy.determineDiscriminatorColumnName(
								hierarchySource.getDiscriminatorSource()
						);
					}
				}
		);

		rootEntityDescriptor.setPolymorphic( true );
		rootEntityDescriptor.setDiscriminatorInsertable( hierarchySource.getDiscriminatorSource().isInserted() );

		// todo : currently isForced() is defined as boolean, not Boolean
		//		although it has always been that way (DTD too)
		final boolean force = hierarchySource.getDiscriminatorSource().isForced()
				|| sourceDocument.getBuildingOptions().shouldImplicitlyForceDiscriminatorInSelect();
		rootEntityDescriptor.setForceDiscriminator( force );
	}
