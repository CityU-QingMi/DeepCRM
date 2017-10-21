	 @Test
	 public void testMultiTenancy() throws Exception {
		  final Item item = new Item("my item", "description" );

		  withTxSession(sessionFactory().withOptions().tenantIdentifier(DB1), s -> s.persist(item));

		  for (int i = 0; i < 5; ++i) { // make sure we get something cached
				withTxSession(sessionFactory().withOptions().tenantIdentifier(DB1), s -> {
						  Item item2 = s.get(Item.class, item.getId());
						  assertNotNull(item2);
						  assertEquals(item.getName(), item2.getName());
				});

		  }
		  // The table ITEMS is not created in DB2 - we would get just an exception
//        for (int i = 0; i < 5; ++i) { // make sure we get something cached
//            withTx(tm, new Callable<Void>() {
//                @Override
//                public Void call() throws Exception {
//                    Session s = sessionFactory().withOptions().tenantIdentifier(DB2).openSession();
//                    s.getTransaction().begin();
//                    Item item2 = s.get(Item.class, id);
//                    s.getTransaction().commit();
//                    s.close();
//                    assertNull(item2);
//                    return null;
//                }
//            });
//        }
		  EntityRegionImpl region = (EntityRegionImpl) sessionFactory().getSecondLevelCacheRegion(Item.class.getName());
		  AdvancedCache localCache = region.getCache().withFlags(Flag.CACHE_MODE_LOCAL);
		  assertEquals(1, localCache.size());
		  try (CloseableIterator iterator = localCache.keySet().iterator()) {
			  assertEquals("CacheKeyImplementation", iterator.next().getClass().getSimpleName());
		  }
	 }
