	private void performExecution() {
		LOG.debugf( "Executing batch size: %s", batchPosition );
		try {
			for ( Map.Entry<String,PreparedStatement> entry : getStatements().entrySet() ) {
				String sql = entry.getKey();
				try {
					final PreparedStatement statement = entry.getValue();
					final int[] rowCounts;
					try {
						getJdbcCoordinator().getJdbcSessionOwner().getJdbcSessionContext().getObserver().jdbcExecuteBatchStart();
						rowCounts = statement.executeBatch();
					}
					finally {
						getJdbcCoordinator().getJdbcSessionOwner().getJdbcSessionContext().getObserver().jdbcExecuteBatchEnd();
					}
					checkRowCounts( rowCounts, statement );
				}
				catch ( SQLException e ) {
					abortBatch();
					LOG.unableToExecuteBatch( e, sql );
					throw sqlExceptionHelper().convert( e, "could not execute batch", sql );
				}
				catch ( RuntimeException re ) {
					abortBatch();
					LOG.unableToExecuteBatch( re, sql );
					throw re;
				}
			}
		}
		finally {
			batchPosition = 0;
		}
	}
