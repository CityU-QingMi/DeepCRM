   @Test
   public void testEvictUpdate() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch preFlushLatch = new CountDownLatch(1);
      CountDownLatch postEvictLatch = new CountDownLatch(1);
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);

      Future<Boolean> first = evictWait(itemId, loadBarrier, null, postEvictLatch);
      Future<Boolean> second = updateFlushWait(itemId, loadBarrier, preFlushLatch, flushLatch, commitLatch);
      awaitOrThrow(postEvictLatch);

      assertEmptyCache();

      preFlushLatch.countDown();
      awaitOrThrow(flushLatch);
      // The tombstone from update has overwritten the eviction tombstone as it has timestamp = now + 60s
      assertTombstone(1);

      commitLatch.countDown();
      first.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      second.get(WAIT_TIMEOUT, TimeUnit.SECONDS);

      // Since evict was executed during the update, we cannot insert the entry into cache
      assertSingleCacheEntry();

      TIME_SERVICE.advance(timeout + 1);
      assertSingleCacheEntry();
   }
