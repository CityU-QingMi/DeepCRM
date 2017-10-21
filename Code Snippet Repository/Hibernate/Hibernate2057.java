	@Override
	public void end() {
		log.infof(
				"Session Metrics {\n" +
						"    %s nanoseconds spent acquiring %s JDBC connections;\n" +
						"    %s nanoseconds spent releasing %s JDBC connections;\n" +
						"    %s nanoseconds spent preparing %s JDBC statements;\n" +
						"    %s nanoseconds spent executing %s JDBC statements;\n" +
						"    %s nanoseconds spent executing %s JDBC batches;\n" +
						"    %s nanoseconds spent performing %s L2C puts;\n" +
						"    %s nanoseconds spent performing %s L2C hits;\n" +
						"    %s nanoseconds spent performing %s L2C misses;\n" +
						"    %s nanoseconds spent executing %s flushes (flushing a total of %s entities and %s collections);\n" +
						"    %s nanoseconds spent executing %s partial-flushes (flushing a total of %s entities and %s collections)\n" +
						"}",
				jdbcConnectionAcquisitionTime,
				jdbcConnectionAcquisitionCount,
				jdbcConnectionReleaseTime,
				jdbcConnectionReleaseCount,
				jdbcPrepareStatementTime,
				jdbcPrepareStatementCount,
				jdbcExecuteStatementTime,
				jdbcExecuteStatementCount,
				jdbcExecuteBatchTime,
				jdbcExecuteBatchCount,
				cachePutTime,
				cachePutCount,
				cacheHitTime,
				cacheHitCount,
				cacheMissTime,
				cacheMissCount,
				flushTime,
				flushCount,
				flushEntityCount,
				flushCollectionCount,
				partialFlushTime,
				partialFlushCount,
				partialFlushEntityCount,
				partialFlushCollectionCount
		);
	}
