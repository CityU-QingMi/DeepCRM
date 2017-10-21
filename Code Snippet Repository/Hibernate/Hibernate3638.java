	private void performOrderedBatchLoad(
			List<Serializable> idsInBatch,
			LockOptions lockOptions,
			OuterJoinLoadable persister,
			SharedSessionContractImplementor session) {
		final int batchSize =  idsInBatch.size();
		final DynamicEntityLoader batchingLoader = new DynamicEntityLoader(
				persister,
				batchSize,
				lockOptions,
				session.getFactory(),
				session.getLoadQueryInfluencers()
		);

		final Serializable[] idsInBatchArray = idsInBatch.toArray( new Serializable[ idsInBatch.size() ] );

		QueryParameters qp = buildMultiLoadQueryParameters( persister, idsInBatchArray, lockOptions );
		batchingLoader.doEntityBatchFetch( session, qp, idsInBatchArray );

		idsInBatch.clear();
	}
