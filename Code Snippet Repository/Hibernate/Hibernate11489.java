	public void testEvictAll() throws Exception {
		withSessionFactoriesAndRegions(2, (sessionFactories, regions) -> {
			GeneralDataRegion localRegion = regions.get(0);
			GeneralDataRegion remoteRegion = regions.get(1);
			AdvancedCache localCache = ((BaseGeneralDataRegion) localRegion).getCache();
			AdvancedCache remoteCache = ((BaseGeneralDataRegion) remoteRegion).getCache();
			SharedSessionContractImplementor localSession = (SharedSessionContractImplementor) sessionFactories.get(0).openSession();
			SharedSessionContractImplementor remoteSession = (SharedSessionContractImplementor) sessionFactories.get(1).openSession();

			try {
				Set localKeys = localCache.keySet();
				assertEquals( "No valid children in " + localKeys, 0, localKeys.size() );

				Set remoteKeys = remoteCache.keySet();
				assertEquals( "No valid children in " + remoteKeys, 0, remoteKeys.size() );

				assertNull( "local is clean", localRegion.get(null, KEY ) );
				assertNull( "remote is clean", remoteRegion.get(null, KEY ) );

				localRegion.put(localSession, KEY, VALUE1);
				assertEquals( VALUE1, localRegion.get(null, KEY ) );

				remoteRegion.put(remoteSession, KEY, VALUE1);
				assertEquals( VALUE1, remoteRegion.get(null, KEY ) );

				localRegion.evictAll();

				// This should re-establish the region root node in the optimistic case
				assertNull( localRegion.get(null, KEY ) );
				localKeys = localCache.keySet();
				assertEquals( "No valid children in " + localKeys, 0, localKeys.size() );

				// Re-establishing the region root on the local node doesn't
				// propagate it to other nodes. Do a get on the remote node to re-establish
				// This only adds a node in the case of optimistic locking
				assertEquals( null, remoteRegion.get(null, KEY ) );
				remoteKeys = remoteCache.keySet();
				assertEquals( "No valid children in " + remoteKeys, 0, remoteKeys.size() );

				assertEquals( "local is clean", null, localRegion.get(null, KEY ) );
				assertEquals( "remote is clean", null, remoteRegion.get(null, KEY ) );
			} finally {
				localSession.close();
				remoteSession.close();
			}

		});
	}
