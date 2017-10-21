	protected void evictOrRemoveTest(final boolean evict) throws Exception {
		final Object KEY = generateNextKey();
		assertEquals(0, localRegion.getCache().size());
		assertEquals(0, remoteRegion.getCache().size());

		CountDownLatch localPutFromLoadLatch = expectRemotePutFromLoad(remoteRegion.getCache(), localRegion.getCache());
		CountDownLatch remotePutFromLoadLatch = expectRemotePutFromLoad(localRegion.getCache(), remoteRegion.getCache());

		SharedSessionContractImplementor s1 = mockedSession();
		assertNull("local is clean", localAccessStrategy.get(s1, KEY, s1.getTimestamp()));
		SharedSessionContractImplementor s2 = mockedSession();
		assertNull("remote is clean", remoteAccessStrategy.get(s2, KEY, s2.getTimestamp()));

		SharedSessionContractImplementor s3 = mockedSession();
		localAccessStrategy.putFromLoad(s3, KEY, VALUE1, s3.getTimestamp(), 1);
		SharedSessionContractImplementor s5 = mockedSession();
		remoteAccessStrategy.putFromLoad(s5, KEY, VALUE1, s5.getTimestamp(), 1);

		// putFromLoad is applied on local node synchronously, but if there's a concurrent update
		// from the other node it can silently fail when acquiring the loc . Then we could try to read
		// before the update is fully applied.
		assertTrue(localPutFromLoadLatch.await(1, TimeUnit.SECONDS));
		assertTrue(remotePutFromLoadLatch.await(1, TimeUnit.SECONDS));

		SharedSessionContractImplementor s4 = mockedSession();
		assertEquals(VALUE1, localAccessStrategy.get(s4, KEY, s4.getTimestamp()));
		SharedSessionContractImplementor s6 = mockedSession();
		assertEquals(VALUE1, remoteAccessStrategy.get(s6, KEY, s6.getTimestamp()));

		SharedSessionContractImplementor session = mockedSession();
		withTx(localEnvironment, session, () -> {
			if (evict) {
				localAccessStrategy.evict(KEY);
			}
			else {
				doRemove(localAccessStrategy, session, KEY);
			}
			return null;
		});

		SharedSessionContractImplementor s7 = mockedSession();
		assertNull(localAccessStrategy.get(s7, KEY, s7.getTimestamp()));
		assertEquals(0, localRegion.getCache().size());
		SharedSessionContractImplementor s8 = mockedSession();
		assertNull(remoteAccessStrategy.get(s8, KEY, s8.getTimestamp()));
		assertEquals(0, remoteRegion.getCache().size());
	}
