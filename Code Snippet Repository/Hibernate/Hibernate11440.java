	public void endInvalidation() {
		synchronized (this) {
			if (--invalidations == 0) {
				lastRegionInvalidation = nextTimestamp();
			}
		}
		if (log.isTraceEnabled()) {
			log.trace( "End invalidating region: " + name );
		}
	}
