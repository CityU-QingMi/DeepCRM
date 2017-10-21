   @Test
   public void testRemoveRolledBack() throws Exception {
      withTxSession(s -> {
         Item item = s.load(Item.class, itemId);
         s.delete(item);
         assertSingleCacheEntry();
         s.flush();
         assertSingleCacheEntry();
         markRollbackOnly(s);
      });
      assertSingleCacheEntry();
   }
