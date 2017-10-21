   @Test
   public void testEvictAndPutFromLoad() throws Exception {
      sessionFactory().getCache().evictEntity(Item.class, itemId);
      assertSingleEmpty();
      TIME_SERVICE.advance(1);

      withTxSession(s -> {
         Item item = s.load(Item.class, itemId);
         assertEquals("Original item", item.getDescription());
      });

      assertSingleCacheEntry();
      TIME_SERVICE.advance(TIMEOUT + 1);
      assertSingleCacheEntry();
   }
