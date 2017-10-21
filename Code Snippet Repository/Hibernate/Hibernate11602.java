   @Test
   public void testStaleReadDuringUpdate() throws Exception {
      ByRef<Object> entryRef = testStaleRead((s, item) -> {
         item.setDescription("Updated item");
         s.update(item);
      });
      assertNotEquals(entryRef.get(), assertSingleCacheEntry());
      withTxSession(s -> {
         Item item = s.load(Item.class, itemId);
         assertEquals("Updated item", item.getDescription());
      });
   }
