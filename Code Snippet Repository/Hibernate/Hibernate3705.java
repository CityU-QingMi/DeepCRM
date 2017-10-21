	@Override
	public void startingCollection(CollectionDefinition collectionDefinition) {
		// see if the EntityDefinition is a root...
		final boolean isRoot = fetchSourceStack.isEmpty();
		if ( ! isRoot ) {
			// if not, this call should represent a fetch which should have been handled in #startingAttribute
			return;
		}

		log.tracef(
				"%s Starting root collection : %s",
				StringHelper.repeat( ">>", fetchSourceStack.size() ),
				collectionDefinition.getCollectionPersister().getRole()
		);

		// if we get here, it is a root
		if ( ! supportsRootCollectionReturns() ) {
			throw new HibernateException( "This strategy does not support root collection returns" );
		}

		final CollectionReturn collectionReturn = new CollectionReturnImpl( collectionDefinition, querySpaces );
		pushToCollectionStack( collectionReturn );
		addRootReturn( collectionReturn );

		associationKeyRegistered(
				new AssociationKey(
						( (Joinable) collectionDefinition.getCollectionPersister() ).getTableName(),
						( (Joinable) collectionDefinition.getCollectionPersister() ).getKeyColumnNames()
				)
		);
	}
