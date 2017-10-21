	@Override
	public void entityCopyDetected(
			Object managedEntity,
			Object mergeEntity1,
			Object mergeEntity2,
			EventSource session) {
		final String entityName = session.getEntityName( managedEntity );
		LOG.trace(
				String.format(
						"More than one representation of the same persistent entity being merged for: %s",
						MessageHelper.infoString(
								entityName,
								session.getIdentifier( managedEntity )
						)
				)
		);
		Set<Object> detachedEntitiesForManaged = null;
		if ( managedToMergeEntitiesXref == null ) {
			// This is the first time multiple representations have been found;
			// instantiate managedToMergeEntitiesXref.
			managedToMergeEntitiesXref = new IdentityHashMap<Object, Set<Object>>();
		}
		else {
			// Get any existing representations that have already been found.
			detachedEntitiesForManaged = managedToMergeEntitiesXref.get( managedEntity );
		}
		if ( detachedEntitiesForManaged == null ) {
			// There were no existing representations for this particular managed entity;
			detachedEntitiesForManaged = new IdentitySet();
			managedToMergeEntitiesXref.put( managedEntity, detachedEntitiesForManaged );
			incrementEntityNameCount( entityName );
		}
		// Now add the detached representation for the managed entity;
		detachedEntitiesForManaged.add( mergeEntity1 );
		detachedEntitiesForManaged.add( mergeEntity2 );
	}
