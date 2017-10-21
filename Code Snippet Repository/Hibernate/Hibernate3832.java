	public EntityReferenceProcessingState getIdentifierResolutionContext(ResultSetProcessingContext context) {
		final EntityReferenceProcessingState entityReferenceProcessingState = context.getProcessingState( entityReturn );

		if ( entityReferenceProcessingState == null ) {
			throw new AssertionFailure(
					String.format(
							"Could not locate EntityReferenceProcessingState for root entity return [%s (%s)]",
							entityReturn.getPropertyPath().getFullPath(),
							entityReturn.getEntityPersister().getEntityName()
					)
			);
		}

		return entityReferenceProcessingState;
	}
