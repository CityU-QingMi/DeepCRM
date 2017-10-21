	 @Test
	 public void testNoTenancy() throws Exception {
		  final Item item = new Item("my item", "description" );

		  withTxSession(s -> s.persist(item));
		  for (int i = 0; i < 5; ++i) { // make sure we get something cached
				withTxSession(s -> {
					  Item item2 = s.get(Item.class, item.getId());
					  assertNotNull(item2);
					  assertEquals(item.getName(), item2.getName());
				});

		  }
		  EntityRegionImpl region = (EntityRegionImpl) sessionFactory().getSecondLevelCacheRegion(Item.class.getName());
		  AdvancedCache localCache = region.getCache().withFlags(Flag.CACHE_MODE_LOCAL);
		  assertEquals(1, localCache.size());
		  try (CloseableIterator iterator = localCache.keySet().iterator()) {
			  assertEquals(sessionFactory().getClassMetadata(Item.class).getIdentifierType().getReturnedClass(), iterator.next().getClass());
		  }
	 }
