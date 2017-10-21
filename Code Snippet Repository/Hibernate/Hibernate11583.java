	@Test
	public void testStaleWritesLeaveCacheConsistent() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();

		ByRef<VersionedItem> itemRef = new ByRef<>(null);
		withTxSession(s -> {
			VersionedItem item = new VersionedItem();
			item.setName( "steve" );
			item.setDescription( "steve's item" );
			s.save( item );
			itemRef.set(item);
		});

		final VersionedItem item = itemRef.get();
		Long initialVersion = item.getVersion();

		// manually revert the version property
		item.setVersion( new Long( item.getVersion().longValue() - 1 ) );

		try {
			withTxSession(s -> s.update(item));
			fail("expected stale write to fail");
		}
		catch (Exception e) {
			log.debug("Rollback was expected", e);
		}

		// check the version value in the cache...
		SecondLevelCacheStatistics slcs = stats.getSecondLevelCacheStatistics( VersionedItem.class.getName() );

		Object entry = slcs.getEntries().get( item.getId() );
		Long cachedVersionValue;
		cachedVersionValue = (Long) ((CacheEntry) entry).getVersion();
		assertEquals(initialVersion.longValue(), cachedVersionValue.longValue());

		withTxSession(s -> {
			VersionedItem item2 = s.load( VersionedItem.class, item.getId() );
			s.delete( item2 );
		});
	}
