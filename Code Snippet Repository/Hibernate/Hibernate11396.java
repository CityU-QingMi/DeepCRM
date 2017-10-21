		public void invalidate(long now) {
			if ( singlePendingPut != null ) {
				if (singlePendingPut.invalidate(now, expirationPeriod)) {
					singlePendingPut = null;
				}
			}
			else if ( fullMap != null ) {
				for ( Iterator<PendingPut> it = fullMap.values().iterator(); it.hasNext(); ) {
					PendingPut pp = it.next();
					if (pp.invalidate(now, expirationPeriod)) {
						it.remove();
					}
				}
			}
		}
