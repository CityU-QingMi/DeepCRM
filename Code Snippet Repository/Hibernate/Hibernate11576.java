	@Test
	public void testEntityCacheContentsAfterEvictAll() throws Exception {
		final List<Citizen> citizens = saveSomeCitizens();

		withTxSession(s -> {
			Cache cache = s.getSessionFactory().getCache();

			Statistics stats = sessionFactory().getStatistics();
			SecondLevelCacheStatistics slcStats = stats.getSecondLevelCacheStatistics(Citizen.class.getName());

			assertTrue("2lc entity cache is expected to contain Citizen id = " + citizens.get(0).getId(),
					cache.containsEntity(Citizen.class, citizens.get(0).getId()));
			assertTrue("2lc entity cache is expected to contain Citizen id = " + citizens.get(1).getId(),
					cache.containsEntity(Citizen.class, citizens.get(1).getId()));
			assertEquals(2, slcStats.getPutCount());

			cache.evictEntityRegions();
			TIME_SERVICE.advance(1);

			assertEquals(0, slcStats.getElementCountInMemory());
			assertFalse("2lc entity cache is expected to not contain Citizen id = " + citizens.get(0).getId(),
					cache.containsEntity(Citizen.class, citizens.get(0).getId()));
			assertFalse("2lc entity cache is expected to not contain Citizen id = " + citizens.get(1).getId(),
					cache.containsEntity(Citizen.class, citizens.get(1).getId()));

			Citizen citizen = s.load(Citizen.class, citizens.get(0).getId());
			assertNotNull(citizen);
			assertNotNull(citizen.getFirstname()); // proxy gets resolved
			assertEquals(1, slcStats.getMissCount());

			// cleanup
			markRollbackOnly(s);
		});
	}
