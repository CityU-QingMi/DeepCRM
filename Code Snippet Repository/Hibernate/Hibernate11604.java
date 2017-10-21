   @Test
   public void testEvictUpdateExpiration() throws Exception {
      // since the timestamp for update is based on session open/tx begin time, we have to do this sequentially
      sessionFactory().getCache().evictEntity(Item.class, itemId);
      assertSingleEmpty();
      TIME_SERVICE.advance(1);

      withTxSession(s -> {
         Item item = s.load(Item.class, itemId);
         item.setDescription("Updated item");
         s.update(item);
      });

      assertSingleCacheEntry();
      TIME_SERVICE.advance(timeout + 1);
      assertSingleCacheEntry();
   }
