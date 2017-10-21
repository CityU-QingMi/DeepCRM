	public String toString() {
		return "QueryStatistics"
				+ "[cacheHitCount=" + this.cacheHitCount
				+ ",cacheMissCount=" + this.cacheMissCount
				+ ",cachePutCount=" + this.cachePutCount
				+ ",executionCount=" + this.executionCount
				+ ",executionRowCount=" + this.executionRowCount
				+ ",executionAvgTime=" + this.getExecutionAvgTime()
				+ ",executionMaxTime=" + this.executionMaxTime
				+ ",executionMinTime=" + this.executionMinTime
				+ ']';
	}
