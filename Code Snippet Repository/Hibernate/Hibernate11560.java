   @TestForIssue(jiraKey = "")
   @Test
   public void testFailedRemove() throws Exception {
      AdvancedCache pendingPutsCache = getPendingPutsCache(Item.class);
      assertNoInvalidators(pendingPutsCache);
      final Item item = new Item("before-remove", "bar");
      withTxSession(s -> s.persist(item));

      withTxSession(s -> {
         Item item2 = s.load(Item.class, item.getId());
         assertEquals("before-remove", item2.getName());
         s.remove(item2);
         s.flush();
         s.getTransaction().setRollbackOnly();
      });
      assertNoInvalidators(pendingPutsCache);

      withTxSession(s -> {
         Item item3 = s.load(Item.class, item.getId());
         assertEquals("before-remove", item3.getName());
         s.remove(item3);
      });
      assertNoInvalidators(pendingPutsCache);
   }
