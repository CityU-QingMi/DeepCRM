		public EntityLoader byUniqueKey(String[] keyColumnNames, Type keyType) {
			// capture current values in a new instance of QueryBuildingParametersImpl
			return new EntityLoader(
					persister.getFactory(),
					persister,
					keyColumnNames,
					keyType,
					new QueryBuildingParametersImpl(
							influencers,
							batchSize,
							lockMode,
							lockOptions
					)
			);
		}
