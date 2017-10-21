   @Test
   public void testRemoveUpdateExpiration() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch preFlushLatch = new CountDownLatch(1);
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);

      Future<Boolean> first = removeFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      Future<Boolean> second = updateFlushWait(itemId, loadBarrier, preFlushLatch, null, commitLatch);
      awaitOrThrow(flushLatch);

      // Second update fails due to being unable to lock entry *before* writing the tombstone
      assertTombstone(1);

      preFlushLatch.countDown();
      commitLatch.countDown();
      first.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      second.get(WAIT_TIMEOUT, TimeUnit.SECONDS);

      assertTombstone(1);

      TIME_SERVICE.advance(timeout + 1);
      assertEmptyCache();
   }
