	@Override
	public void finishingCollection(CollectionDefinition collectionDefinition) {
		final boolean isRoot = fetchSourceStack.isEmpty() && collectionReferenceStack.size() == 1;
		if ( !isRoot ) {
			// if not, this call should represent a fetch which will be handled in #finishingAttribute
			return;
		}

		final CollectionReference popped = popFromCollectionStack();
		checkedPoppedCollection( popped, collectionDefinition );

		log.tracef(
				"%s Finished root collection : %s",
				StringHelper.repeat( "<<", fetchSourceStack.size() ),
				collectionDefinition.getCollectionPersister().getRole()
		);
	}
