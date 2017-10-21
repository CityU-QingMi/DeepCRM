	public void add(QueryStats stats) {
		cacheHitCount += stats.getCacheHitCount();
		cacheMissCount += stats.getCacheMissCount();
		cachePutCount += stats.getCachePutCount();
		executionCount += stats.getExecutionCount();
		executionRowCount += stats.getExecutionRowCount();
		executionAvgTime += stats.getExecutionAvgTime();
		executionMaxTime += stats.getExecutionMaxTime();
		executionMinTime += stats.getExecutionMinTime();
	}
