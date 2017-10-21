		public DynamicBatchingCollectionInitializer(
				QueryableCollection collectionPersister,
				int maxBatchSize,
				SessionFactoryImplementor factory,
				LoadQueryInfluencers influencers) {
			super( collectionPersister );
			this.maxBatchSize = maxBatchSize;

			if ( collectionPersister.isOneToMany() ) {
				this.singleKeyLoader = new OneToManyLoader( collectionPersister, 1, factory, influencers );
			}
			else {
				this.singleKeyLoader = new BasicCollectionLoader( collectionPersister, 1, factory, influencers );
			}

			this.batchLoader = new DynamicBatchingCollectionLoader( collectionPersister, factory, influencers );
		}
