		public CollectionLoader byKey() {
			// capture current values in a new instance of QueryBuildingParametersImpl
			final QueryBuildingParameters currentBuildingParameters = new QueryBuildingParametersImpl(
					influencers,
					batchSize,
					LockMode.NONE,
					null
			);
			return new CollectionLoader( collectionPersister, currentBuildingParameters ) ;
		}
