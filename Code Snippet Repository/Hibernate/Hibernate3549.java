		public DynamicBatchingCollectionLoader(
				QueryableCollection collectionPersister,
				SessionFactoryImplementor factory,
				LoadQueryInfluencers influencers) {
			super( collectionPersister, factory, influencers );

			JoinWalker walker = buildJoinWalker( collectionPersister, factory, influencers );
			initFromWalker( walker );
			this.sqlTemplate = walker.getSQLString();
			this.alias = StringHelper.generateAlias( collectionPersister.getRole(), 0 );
			postInstantiate();

			if ( LOG.isDebugEnabled() ) {
				LOG.debugf(
						"SQL-template for dynamic collection [%s] batch-fetching : %s",
						collectionPersister.getRole(),
						sqlTemplate
				);
			}
		}
