	@Override
	public Batch buildBatch(BatchKey key, JdbcCoordinator jdbcCoordinator) {
		final Integer sessionJdbcBatchSize = jdbcCoordinator.getJdbcSessionOwner()
				.getJdbcBatchSize();
		final int jdbcBatchSizeToUse = sessionJdbcBatchSize == null ?
				this.jdbcBatchSize :
				sessionJdbcBatchSize;
		return jdbcBatchSizeToUse > 1
				? new BatchingBatch( key, jdbcCoordinator, jdbcBatchSizeToUse )
				: new NonBatchingBatch( key, jdbcCoordinator );
	}
