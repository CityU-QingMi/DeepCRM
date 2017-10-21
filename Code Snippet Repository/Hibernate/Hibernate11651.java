		@CacheEntryModified
		public void nodeVisisted(CacheEntryModifiedEvent event) {
			// we need isPre since lock is acquired in the commit phase
			if ( !event.isPre() && event.getKey().equals( key ) ) {
				try {
					synchronized (this) {
						if (enabled) {
							triggerLatch.countDown();
							enabled = false;
							blockLatch.await();
						}
					}
				}
				catch (InterruptedException e) {
					log.error( "Interrupted waiting for latch", e );
				}
			}
		}
