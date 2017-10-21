   @Test
   public void testTwoUpdates1() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch preFlushLatch = new CountDownLatch(1);
      CountDownLatch flushLatch1 = new CountDownLatch(1);
      CountDownLatch flushLatch2 = new CountDownLatch(1);
      CountDownLatch commitLatch1 = new CountDownLatch(1);
      CountDownLatch commitLatch2 = new CountDownLatch(1);

      // Note: this is a single node case, we don't have to deal with async replication
      Future<Boolean> update1 = updateFlushWait(itemId, loadBarrier, null, flushLatch1, commitLatch1);
      Future<Boolean> update2 = updateFlushWait(itemId, loadBarrier, preFlushLatch, flushLatch2, commitLatch2);

      awaitOrThrow(flushLatch1);
      assertTombstone(1);

      preFlushLatch.countDown();
      awaitOrThrow(flushLatch2);

      // Second update fails due to being unable to lock entry *before* writing the tombstone
      assertTombstone(1);

      commitLatch2.countDown();
      assertFalse(update2.get(WAIT_TIMEOUT, TimeUnit.SECONDS));
      assertTombstone(1);

      commitLatch1.countDown();
      assertTrue(update1.get(WAIT_TIMEOUT, TimeUnit.SECONDS));
      assertSingleCacheEntry();
   }
