   @Test
   public void testUpdateRemoveExpiration() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch preFlushLatch = new CountDownLatch(1);
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);

      Future<Boolean> first = updateFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      Future<Boolean> second = removeFlushWait(itemId, loadBarrier, preFlushLatch, null, commitLatch);
      awaitOrThrow(flushLatch);

      assertTombstone(1);

      preFlushLatch.countDown();
      commitLatch.countDown();
      first.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      boolean removeSucceeded = second.get(WAIT_TIMEOUT, TimeUnit.SECONDS);

      if (removeSucceeded) {
         assertCacheContains(Tombstone.class);
         TIME_SERVICE.advance(timeout + 1);
         assertEmptyCache();
      } else {
         assertSingleCacheEntry();
         TIME_SERVICE.advance(timeout + 1);
         assertSingleCacheEntry();
      }
   }
