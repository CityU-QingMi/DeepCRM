	@Test
	public void testMultipleEvictAll() throws Exception {
		final List<Citizen> citizens = saveSomeCitizens();

		withTxSession(s -> {
			Cache cache = s.getSessionFactory().getCache();

			cache.evictEntityRegions();
			cache.evictEntityRegions();
		});
		withTxSession(s -> {
			Cache cache = s.getSessionFactory().getCache();

			cache.evictEntityRegions();

			s.delete(s.load(Citizen.class, citizens.get(0).getId()));
			s.delete(s.load(Citizen.class, citizens.get(1).getId()));
		});
	}
