   @TestForIssue(jiraKey = "")
   @Test
   public void testFailedInsert() throws Exception {
      AdvancedCache pendingPutsCache = getPendingPutsCache(Item.class);
      assertNoInvalidators(pendingPutsCache);
      withTxSession(s -> {
         Item i = new Item("inserted", "bar");
         s.persist(i);
         s.flush();
         s.getTransaction().setRollbackOnly();
      });
      assertNoInvalidators(pendingPutsCache);
   }
