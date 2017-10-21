	protected void putFromLoadTestReadOnly(boolean minimal) throws Exception {
		final Object KEY = TestingKeyFactory.generateEntityCacheKey( KEY_BASE + testCount++ );

		CountDownLatch remotePutFromLoadLatch = expectPutFromLoad();

		SharedSessionContractImplementor session = mockedSession();
		withTx(localEnvironment, session, () -> {
			assertNull(localAccessStrategy.get(session, KEY, session.getTimestamp()));
			if (minimal)
				localAccessStrategy.putFromLoad(session, KEY, VALUE1, session.getTimestamp(), 1, true);
			else
				localAccessStrategy.putFromLoad(session, KEY, VALUE1, session.getTimestamp(), 1);
			return null;
		});

		SharedSessionContractImplementor s2 = mockedSession();
		assertEquals(VALUE1, localAccessStrategy.get(s2, KEY, s2.getTimestamp()));
		SharedSessionContractImplementor s3 = mockedSession();
		Object expected;
		if (isUsingInvalidation()) {
			expected = null;
		} else {
			if (accessType != AccessType.NONSTRICT_READ_WRITE) {
				assertTrue(remotePutFromLoadLatch.await(2, TimeUnit.SECONDS));
			}
			expected = VALUE1;
		}
		assertEquals(expected, remoteAccessStrategy.get(s3, KEY, s3.getTimestamp()));
	}
