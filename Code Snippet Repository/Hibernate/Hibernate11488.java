	@Test
	public void testEvict() throws Exception {
		withSessionFactoriesAndRegions(2, ((sessionFactories, regions) -> {
			GeneralDataRegion localRegion = regions.get(0);
			GeneralDataRegion remoteRegion = regions.get(1);
			SharedSessionContractImplementor localSession = (SharedSessionContractImplementor) sessionFactories.get(0).openSession();
			SharedSessionContractImplementor remoteSession = (SharedSessionContractImplementor) sessionFactories.get(1).openSession();
			AdvancedCache localCache = ((BaseRegion) localRegion).getCache();
			AdvancedCache remoteCache = ((BaseRegion) remoteRegion).getCache();
			try {
				assertNull("local is clean", localRegion.get(localSession, KEY));
				assertNull("remote is clean", remoteRegion.get(remoteSession, KEY));

				// If this node is backup owner, it will see the update once as originator and then when getting the value from primary
				boolean isLocalNodeBackupOwner = localCache.getDistributionManager().locate(KEY).indexOf(localCache.getCacheManager().getAddress()) > 0;
				CountDownLatch insertLatch = new CountDownLatch(isLocalNodeBackupOwner ? 3 : 2);
				ExpectingInterceptor.get(localCache).when((ctx, cmd) -> cmd instanceof PutKeyValueCommand).countDown(insertLatch);
				ExpectingInterceptor.get(remoteCache).when((ctx, cmd) -> cmd instanceof PutKeyValueCommand).countDown(insertLatch);

				Transaction tx = localSession.getTransaction();
				tx.begin();
				try {
					localRegion.put(localSession, KEY, VALUE1);
					tx.commit();
				} catch (Exception e) {
					tx.rollback();
					throw e;
				}

				assertTrue(insertLatch.await(2, TimeUnit.SECONDS));
				assertEquals(VALUE1, localRegion.get(localSession, KEY));
				assertEquals(VALUE1, remoteRegion.get(remoteSession, KEY));

				CountDownLatch removeLatch = new CountDownLatch(isLocalNodeBackupOwner ? 3 : 2);
				ExpectingInterceptor.get(localCache).when((ctx, cmd) -> cmd instanceof RemoveCommand).countDown(removeLatch);
				ExpectingInterceptor.get(remoteCache).when((ctx, cmd) -> cmd instanceof RemoveCommand).countDown(removeLatch);

				regionEvict(localRegion);

				assertTrue(removeLatch.await(2, TimeUnit.SECONDS));
				assertEquals(null, localRegion.get(localSession, KEY));
				assertEquals(null, remoteRegion.get(remoteSession, KEY));
			} finally {
				localSession.close();
				remoteSession.close();

				ExpectingInterceptor.cleanup(localCache, remoteCache);
			}
		}));
	}
