	@Override
	public Object readRow(ResultSet resultSet, ResultSetProcessingContextImpl context) throws SQLException {

		final boolean hasEntityReferenceInitializers = CollectionHelper.isNotEmpty( entityReferenceInitializers );

		if ( hasEntityReferenceInitializers ) {
			// 	1) allow entity references to resolve identifiers (in 2 steps)
			for ( EntityReferenceInitializer entityReferenceInitializer : entityReferenceInitializers ) {
				entityReferenceInitializer.hydrateIdentifier( resultSet, context );
			}
			for ( EntityReferenceInitializer entityReferenceInitializer : entityReferenceInitializers ) {
				resolveEntityKey(
						resultSet,
						context,
						entityReferenceInitializer
				);
			}

			// 2) allow entity references to resolve their non-identifier hydrated state and entity instance
			for ( EntityReferenceInitializer entityReferenceInitializer : entityReferenceInitializers ) {
				entityReferenceInitializer.hydrateEntityState( resultSet, context );
			}
		}


		// 3) read the logical row

		Object logicalRow = readLogicalRow( resultSet, context );


		// 4) allow arrays, entities and collections after row callbacks
		if ( hasEntityReferenceInitializers ) {
			for ( EntityReferenceInitializer entityReferenceInitializer : entityReferenceInitializers ) {
				entityReferenceInitializer.finishUpRow( resultSet, context );
			}
		}
		if ( collectionReferenceInitializers != null ) {
			for ( CollectionReferenceInitializer collectionReferenceInitializer : collectionReferenceInitializers ) {
				collectionReferenceInitializer.finishUpRow( resultSet, context );
			}
		}
		if ( arrayReferenceInitializers != null ) {
			for ( CollectionReferenceInitializer arrayReferenceInitializer : arrayReferenceInitializers ) {
				arrayReferenceInitializer.finishUpRow( resultSet, context );
			}
		}

		return logicalRow;
	}
