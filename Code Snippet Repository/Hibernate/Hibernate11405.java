	public void endInvalidatingRegion() {
		synchronized (this) {
			if (--regionInvalidations == 0) {
				regionInvalidationTimestamp = regionFactory.nextTimestamp();
				if (trace) {
					log.tracef("Finished invalidating region %s at %d", cache.getName(), regionInvalidationTimestamp);
				}
			}
			else {
				if (trace) {
					log.tracef("Finished invalidating region %s, but there are %d ongoing invalidations", cache.getName(), regionInvalidations);
				}
			}
		}
	}
