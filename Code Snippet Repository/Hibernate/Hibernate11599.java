   @Test
   public void testTwoRemoves() throws Exception {
      CyclicBarrier loadBarrier = new CyclicBarrier(2);
      CountDownLatch flushLatch = new CountDownLatch(2);
      CountDownLatch commitLatch = new CountDownLatch(1);

      Future<Boolean> first = removeFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      Future<Boolean> second = removeFlushWait(itemId, loadBarrier, null, flushLatch, commitLatch);
      awaitOrThrow(flushLatch);

      assertSingleCacheEntry();

      commitLatch.countDown();
      boolean firstResult = first.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      boolean secondResult = second.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      assertTrue(firstResult != secondResult);

      assertSingleEmpty();

      TIME_SERVICE.advance(timeout + 1);
      assertEmptyCache();
   }
