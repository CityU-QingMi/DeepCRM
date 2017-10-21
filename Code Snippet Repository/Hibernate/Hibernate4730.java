	@Override
	public void naturalIdQueryExecuted(String regionName, long time) {
		naturalIdQueryExecutionCount.getAndIncrement();
		boolean isLongestQuery;
		//noinspection StatementWithEmptyBody
		for ( long old = naturalIdQueryExecutionMaxTime.get();
				( isLongestQuery = time > old ) && ( !naturalIdQueryExecutionMaxTime.compareAndSet( old, time ) );
				old = naturalIdQueryExecutionMaxTime.get() ) {
			// nothing to do here given the odd loop structure...
		}
		if ( isLongestQuery && regionName != null ) {
			naturalIdQueryExecutionMaxTimeRegion = regionName;
		}
		if ( regionName != null ) {
			getNaturalIdCacheStatistics( regionName ).queryExecuted( time );
		}
	}
