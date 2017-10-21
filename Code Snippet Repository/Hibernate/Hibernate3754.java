	@Override
	public ExpandingCollectionQuerySpace makeCollectionQuerySpace(
			String uid,
			CollectionPersister collectionPersister,
			boolean canJoinsBeRequired) {

		checkQuerySpaceDoesNotExist( uid );

		final ExpandingCollectionQuerySpace space = new CollectionQuerySpaceImpl(
				collectionPersister,
				uid,
				this,
				canJoinsBeRequired
		);
		registerQuerySpace( space );

		return space;
	}
