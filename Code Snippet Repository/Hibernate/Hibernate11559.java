   @TestForIssue(jiraKey = "")
   @Test
   public void testFailedUpdate() throws Exception {
      AdvancedCache pendingPutsCache = getPendingPutsCache(Item.class);
      assertNoInvalidators(pendingPutsCache);
      final Item item = new Item("before-update", "bar");
      withTxSession(s -> s.persist(item));

      withTxSession(s -> {
         Item item2 = s.load(Item.class, item.getId());
         assertEquals("before-update", item2.getName());
         item2.setName("after-update");
         s.persist(item2);
         s.flush();
         s.flush(); // workaround for HHH-11312
         s.getTransaction().setRollbackOnly();
      });
      assertNoInvalidators(pendingPutsCache);

      withTxSession(s -> {
         Item item3 = s.load(Item.class, item.getId());
         assertEquals("before-update", item3.getName());
         s.remove(item3);
      });
      assertNoInvalidators(pendingPutsCache);
   }
