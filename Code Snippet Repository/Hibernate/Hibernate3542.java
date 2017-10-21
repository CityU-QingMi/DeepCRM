	protected BasicCollectionLoader(
			QueryableCollection collectionPersister,
			int batchSize,
			String subquery,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( collectionPersister, factory, loadQueryInfluencers );

		JoinWalker walker = new BasicCollectionJoinWalker(
				collectionPersister,
				batchSize,
				subquery,
				factory,
				loadQueryInfluencers
		);
		initFromWalker( walker );

		postInstantiate();

		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Static select for collection %s: %s", collectionPersister.getRole(), getSQLString() );
		}
	}
