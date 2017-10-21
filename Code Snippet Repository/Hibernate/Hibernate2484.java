	@Override
	public void entityCopyDetected(
			Object managedEntity,
			Object mergeEntity1,
			Object mergeEntity2,
			EventSource session) {
		if ( mergeEntity1 == managedEntity && mergeEntity2 == managedEntity) {
			throw new AssertionFailure( "entity1 and entity2 are the same as managedEntity; must be different." );
		}
		final String managedEntityString = 	MessageHelper.infoString(
				session.getEntityName( managedEntity ),
				session.getIdentifier( managedEntity )
		);
		throw new IllegalStateException(
				"Multiple representations of the same entity " + managedEntityString + " are being merged. " +
						getManagedOrDetachedEntityString( managedEntity, mergeEntity1 ) + "; " +
						getManagedOrDetachedEntityString( managedEntity, mergeEntity2 )
		);
	}
