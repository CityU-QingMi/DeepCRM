	@Override
	public void finishingEntity(EntityDefinition entityDefinition) {
		// Only process the entityDefinition if it is for the root return.
		final FetchSource currentSource = currentSource();
		final boolean isRoot = EntityReturn.class.isInstance( currentSource ) &&
				entityDefinition.getEntityPersister().equals( EntityReturn.class.cast( currentSource ).getEntityPersister() );
		if ( !isRoot ) {
			// if not, this call should represent a fetch which will be handled in #finishingAttribute
			return;
		}

		// if we get here, it is a root
		final ExpandingFetchSource popped = popFromStack();
		checkPoppedEntity( popped, entityDefinition );

		log.tracef(
				"%s Finished root entity : %s",
				StringHelper.repeat( "<<", fetchSourceStack.size() ),
				entityDefinition.getEntityPersister().getEntityName()
		);
	}
