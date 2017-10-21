   @Test
   public void testTombstoneExpiration() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch flushLatch = new CountDownLatch(2);
      CountDownLatch commitLatch = new CountDownLatch(1);

      Future<Boolean> first = removeFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      Future<Boolean> second = removeFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      awaitOrThrow(flushLatch);

      // Second remove fails due to being unable to lock entry *before* writing the tombstone
      assertTombstone(1);

      commitLatch.countDown();
      first.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      second.get(WAIT_TIMEOUT, TimeUnit.SECONDS);

      // after commit, the tombstone should still be in memory for some time (though, updatable)
      assertTombstone(1);

      TIME_SERVICE.advance(timeout + 1);
      assertEmptyCache();
   }
