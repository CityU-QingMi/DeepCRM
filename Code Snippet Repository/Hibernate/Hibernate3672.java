		public LegacyBatchingEntityLoader(
				OuterJoinLoadable persister,
				int maxBatchSize,
				LockMode lockMode,
				SessionFactoryImplementor factory,
				LoadQueryInfluencers loadQueryInfluencers) {
			super( persister );
			this.batchSizes = ArrayHelper.getBatchSizes( maxBatchSize );
			this.loaders = new EntityLoader[ batchSizes.length ];
			final EntityLoader.Builder entityLoaderBuilder = EntityLoader.forEntity( persister )
					.withInfluencers( loadQueryInfluencers )
					.withLockMode( lockMode );
			for ( int i = 0; i < batchSizes.length; i++ ) {
				this.loaders[i] = entityLoaderBuilder.withBatchSize( batchSizes[i] ).byPrimaryKey();
			}
		}
