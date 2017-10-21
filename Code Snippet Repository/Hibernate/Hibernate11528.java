	private void registeredPutWithInterveningRemovalTest(
			final boolean transactional, final boolean removeRegion)
			throws Exception {
		PutFromLoadValidator testee = new PutFromLoadValidator(cache, regionFactory(cm));
		try {
			long txTimestamp = TIME_SERVICE.wallClockTime();
			if (transactional) {
				tm.begin();
			}
			SharedSessionContractImplementor session1 = mock(SharedSessionContractImplementor.class);
			SharedSessionContractImplementor session2 = mock(SharedSessionContractImplementor.class);
			testee.registerPendingPut(session1, KEY1, txTimestamp);
			if (removeRegion) {
				testee.beginInvalidatingRegion();
			} else {
				testee.beginInvalidatingKey(session2, KEY1);
			}

			PutFromLoadValidator.Lock lock = testee.acquirePutFromLoadLock(session1, KEY1, txTimestamp);
			try {
				assertNull(lock);
			}
			finally {
				if (lock != null) {
					testee.releasePutFromLoadLock(KEY1, lock);
				}
				if (removeRegion) {
					testee.endInvalidatingRegion();
				} else {
					testee.endInvalidatingKey(session2, KEY1);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
