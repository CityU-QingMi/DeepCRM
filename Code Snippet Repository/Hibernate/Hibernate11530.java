		@Override
		public Void call() throws Exception {
			if (removeRegion) {
				boolean success = putFromLoadValidator.beginInvalidatingRegion();
				assertTrue(success);
				putFromLoadValidator.endInvalidatingRegion();;
			} else {
				SharedSessionContractImplementor session = mock (SharedSessionContractImplementor.class);
				boolean success = putFromLoadValidator.beginInvalidatingKey(session, KEY1);
				assertTrue(success);
				success = putFromLoadValidator.endInvalidatingKey(session, KEY1);
				assertTrue(success);
			}
			// if we go for the timestamp-based approach, invalidation in the same millisecond
			// as the registerPendingPut/acquirePutFromLoad lock results in failure.
			TIME_SERVICE.advance(1);
			return null;
		}
