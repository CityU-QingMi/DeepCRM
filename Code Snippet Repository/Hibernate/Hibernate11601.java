   @Test
   public void testUpdateRolledBack() throws Exception {
      ByRef<Object> entryRef = new ByRef<>(null);
      withTxSession(s -> {
         Item item = s.load(Item.class, itemId);
         item.getDescription();
         Object prevEntry = assertSingleCacheEntry();
         entryRef.set(prevEntry);
         item.setDescription("Updated item");
         s.update(item);
         assertEquals(prevEntry, assertSingleCacheEntry());
         s.flush();
         assertEquals(prevEntry, assertSingleCacheEntry());
         markRollbackOnly(s);
      });
      assertEquals(entryRef.get(), assertSingleCacheEntry());
   }
