   @Test
   public void testEvictPutFromLoad() throws Exception {
      sessionFactory().getCache().evictEntity(Item.class, itemId);
      assertEmptyCache();

      TIME_SERVICE.advance(1);
      assertItemDescription("Original item");
      assertSingleCacheEntry();

      TIME_SERVICE.advance(timeout + 2);
      assertSingleCacheEntry();
   }
