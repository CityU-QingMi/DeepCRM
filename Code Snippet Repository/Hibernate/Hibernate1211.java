	private static void bindDiscriminatorColumnToRootPersistentClass(
			RootClass rootClass,
			Ejb3DiscriminatorColumn discriminatorColumn,
			Map<String, Join> secondaryTables,
			PropertyHolder propertyHolder,
			MetadataBuildingContext context) {
		if ( rootClass.getDiscriminator() == null ) {
			if ( discriminatorColumn == null ) {
				throw new AssertionFailure( "discriminator column should have been built" );
			}
			discriminatorColumn.setJoins( secondaryTables );
			discriminatorColumn.setPropertyHolder( propertyHolder );
			SimpleValue discriminatorColumnBinding = new SimpleValue( context.getMetadataCollector(), rootClass.getTable() );
			rootClass.setDiscriminator( discriminatorColumnBinding );
			discriminatorColumn.linkWithValue( discriminatorColumnBinding );
			discriminatorColumnBinding.setTypeName( discriminatorColumn.getDiscriminatorTypeName() );
			rootClass.setPolymorphic( true );
			if ( LOG.isTraceEnabled() ) {
				LOG.tracev( "Setting discriminator for entity {0}", rootClass.getEntityName() );
			}
		}
	}
