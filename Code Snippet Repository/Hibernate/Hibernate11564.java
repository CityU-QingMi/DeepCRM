	@Test
	public void testRedeployment() throws Exception {
		addEntityCheckCache( sessionFactory() );
		bindToJndi = false;
		rebuildSessionFactory();

		addEntityCheckCache( sessionFactory() );
		JndiInfinispanRegionFactory regionFactory = (JndiInfinispanRegionFactory) sessionFactory().getSettings().getRegionFactory();
		Cache cache = regionFactory.getCacheManager().getCache( Item.class.getName() );
		assertEquals( ComponentStatus.RUNNING, cache.getStatus() );
	}
