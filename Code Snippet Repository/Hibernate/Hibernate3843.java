	private void handlePotentiallyEmptyCollectionRootReturns(
			LoadPlan loadPlan,
			Serializable[] collectionKeys,
			ResultSet resultSet,
			SharedSessionContractImplementor session) {
		if ( collectionKeys == null ) {
			// this is not a collection initializer (and empty collections will be detected by looking for
			// the owner's identifier in the result set)
			return;
		}

		// this is a collection initializer, so we must create a collection
		// for each of the passed-in keys, to account for the possibility
		// that the collection is empty and has no rows in the result set
		//
		// todo : move this inside CollectionReturn ?
		CollectionPersister persister = ( (CollectionReturn) loadPlan.getReturns().get( 0 ) ).getCollectionPersister();
		for ( Serializable key : collectionKeys ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf(
						"Preparing collection intializer : %s",
							MessageHelper.collectionInfoString( persister, key, session.getFactory() )
				);
			}
			session.getPersistenceContext()
					.getLoadContexts()
					.getCollectionLoadContext( resultSet )
					.getLoadingCollection( persister, key );
		}
	}
