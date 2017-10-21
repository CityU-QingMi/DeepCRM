   @Test
   public void testUpdateEvictExpiration() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch preEvictLatch = new CountDownLatch(1);
      CountDownLatch postEvictLatch = new CountDownLatch(1);
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);

      Future<Boolean> first = updateFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      Future<Boolean> second = evictWait(itemId, loadBarrier, preEvictLatch, postEvictLatch);
      awaitOrThrow(flushLatch);

      assertTombstone(1);

      preEvictLatch.countDown();
      awaitOrThrow(postEvictLatch);
      assertTombstone(1);

      commitLatch.countDown();
      first.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      second.get(WAIT_TIMEOUT, TimeUnit.SECONDS);

      assertSingleCacheEntry();

      TIME_SERVICE.advance(timeout + 1);

      assertSingleCacheEntry();
   }
