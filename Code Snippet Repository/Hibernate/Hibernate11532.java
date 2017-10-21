		@Override
		public Void call() throws Exception {
			try {
				long txTimestamp = TIME_SERVICE.wallClockTime(); // this should be acquired before UserTransaction.begin()
				SharedSessionContractImplementor session = mock (SharedSessionContractImplementor.class);
				PutFromLoadValidator.Lock lock = testee.acquirePutFromLoadLock(session, KEY1, txTimestamp);
				try {
					if (expectSuccess) {
						assertNotNull(lock);
					} else {
						assertNull(lock);
					}
				}
				finally {
					if (lock != null) {
						testee.releasePutFromLoadLock(KEY1, lock);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return null;
		}
