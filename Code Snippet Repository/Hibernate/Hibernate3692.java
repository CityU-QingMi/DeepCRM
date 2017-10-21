	@Override
	public void finishingCollectionIndex(CollectionIndexDefinition indexDefinition) {
		final Type indexType = indexDefinition.getType();

		if ( indexType.isAnyType() ) {
			// nothing to do because the index graph was not pushed in #startingCollectionIndex.
		}
		else if ( indexType.isEntityType() || indexType.isComponentType() ) {
			// todo : validate the stack?
			final ExpandingFetchSource fetchSource = popFromStack();
			if ( !CollectionFetchableIndex.class.isInstance( fetchSource ) ) {
				throw new WalkingException(
						"CollectionReference did not return an expected index graph : " +
								indexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
				);
			}
		}

		log.tracef(
				"%s Finished collection index graph : %s",
				StringHelper.repeat( "<<", fetchSourceStack.size() ),
				indexDefinition.getCollectionDefinition().getCollectionPersister().getRole()
		);
	}
