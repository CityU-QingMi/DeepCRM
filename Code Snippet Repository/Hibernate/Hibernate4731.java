	@Override
	public void queryExecuted(String hql, int rows, long time) {
		LOG.hql(hql, time, (long) rows );
		queryExecutionCount.getAndIncrement();
		boolean isLongestQuery;
		//noinspection StatementWithEmptyBody
		for ( long old = queryExecutionMaxTime.get();
				( isLongestQuery = time > old ) && ( !queryExecutionMaxTime.compareAndSet( old, time ) );
				old = queryExecutionMaxTime.get() ) {
			// nothing to do here given the odd loop structure...
		}
		if ( isLongestQuery ) {
			queryExecutionMaxTimeQueryString = hql;
		}
		if ( hql != null ) {
			ConcurrentQueryStatisticsImpl qs = getQueryStatistics( hql );
			qs.executed( rows, time );
		}
	}
