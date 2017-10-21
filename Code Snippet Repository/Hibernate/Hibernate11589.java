   @TestForIssue(jiraKey = "")
   @Test
   public void testEvictPutFromLoadDuringUpdate() throws Exception {
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);
      CyclicBarrier putFromLoadBarrier = new CyclicBarrier(2);

      Future<?> putFromLoad = blockedPutFromLoad(putFromLoadBarrier);

      Future<Boolean> update = updateFlushWait(itemId, null, null, flushLatch, commitLatch);
      // Flush stores FutureUpdate(timestamp, null)
      awaitOrThrow(flushLatch);

      sessionFactory().getCache().evictEntity(Item.class, itemId);

      commitLatch.countDown();
      update.get(WAIT_TIMEOUT, TimeUnit.SECONDS);

      unblockPutFromLoad(putFromLoadBarrier, putFromLoad);

      assertItemDescription("Updated item");
   }
