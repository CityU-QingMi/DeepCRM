		@Override
		public Void call() throws Exception {
			try {
				long txTimestamp = TIME_SERVICE.wallClockTime(); // this should be acquired before UserTransaction.begin()
				SharedSessionContractImplementor session = mock (SharedSessionContractImplementor.class);
				putFromLoadValidator.registerPendingPut(session, KEY1, txTimestamp);

				PutFromLoadValidator.Lock lock = putFromLoadValidator.acquirePutFromLoadLock(session, KEY1, txTimestamp);
				try {
					assertNotNull(lock);
				} finally {
					if (lock != null) {
						putFromLoadValidator.releasePutFromLoadLock(KEY1, lock);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return null;
		}
