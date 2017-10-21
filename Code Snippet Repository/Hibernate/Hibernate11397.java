		private void gc() {
			assert fullMap != null;
			long now = regionFactory.nextTimestamp();
			log.tracef("Contains %d, doing GC at %d, expiration %d", size(), now, expirationPeriod);
			for ( Iterator<PendingPut> it = fullMap.values().iterator(); it.hasNext(); ) {
				PendingPut pp = it.next();
				if (pp.gc(now, expirationPeriod)) {
					it.remove();
				}
			}
		}
