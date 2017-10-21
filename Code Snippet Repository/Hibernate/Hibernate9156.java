		@Override
		public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
			// Synchronize load and update activities
			try {
				if (waitLatch != null) {
					waitLatch.countDown();
					waitLatch = null;
				}
				if (blockLatch != null) {
					blockLatch.await();
					blockLatch = null;
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			}
			return true;
		}
