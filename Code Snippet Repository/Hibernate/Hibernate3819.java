	private void resolveEntityKey(
			ResultSet resultSet,
			ResultSetProcessingContextImpl context,
			EntityReferenceInitializer entityReferenceInitializer) throws SQLException {
		final EntityReference entityReference = entityReferenceInitializer.getEntityReference();
		final EntityIdentifierDescription identifierDescription = entityReference.getIdentifierDescription();

		if ( identifierDescription.hasFetches() || identifierDescription.hasBidirectionalEntityReferences() ) {
			resolveEntityKey( resultSet, context, (FetchSource) identifierDescription );
		}
		entityReferenceInitializer.resolveEntityKey( resultSet, context );
	}
