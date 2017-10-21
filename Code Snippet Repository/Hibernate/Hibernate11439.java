	public void beginInvalidation() {
		if (log.isTraceEnabled()) {
			log.trace( "Begin invalidating region: " + name );
		}
		synchronized (this) {
			lastRegionInvalidation = Long.MAX_VALUE;
			++invalidations;
		}
		runInvalidation(getCurrentTransaction() != null);
	}
