   @Before
   public void insertAndClearCache() throws Exception {
      region = sessionFactory().getSecondLevelCacheRegion(Item.class.getName());
      entityCache = ((EntityRegionImpl) region).getCache();
      timeout = ((EntityRegionImpl) region).getRegionFactory().getPendingPutsCacheConfiguration().expiration().maxIdle();
      Item item = new Item("my item", "Original item");
      withTxSession(s -> s.persist(item));
      entityCache.clear();
      assertEquals("Cache is not empty", Collections.EMPTY_SET, entityCache.keySet());
      itemId = item.getId();
      log.info("Insert and clear finished");
   }
