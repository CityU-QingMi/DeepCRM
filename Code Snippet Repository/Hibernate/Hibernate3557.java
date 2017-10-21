	public OneToManyLoader(
			QueryableCollection oneToManyPersister,
			int batchSize,
			String subquery,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( oneToManyPersister, factory, loadQueryInfluencers );

		JoinWalker walker = new OneToManyJoinWalker(
				oneToManyPersister,
				batchSize,
				subquery,
				factory,
				loadQueryInfluencers
		);
		initFromWalker( walker );

		postInstantiate();
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Static select for one-to-many %s: %s", oneToManyPersister.getRole(), getSQLString() );
		}
	}
