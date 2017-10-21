   @Test
   public void testEvictUpdate2() throws Exception {
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);

      sessionFactory().getCache().evictEntity(Item.class, itemId);
      // When the cache was empty, the tombstone is not stored
      assertEmptyCache();

      TIME_SERVICE.advance(1);
      Future<Boolean> update = updateFlushWait(itemId, null, null, flushLatch, commitLatch);
      awaitOrThrow(flushLatch);
      assertTombstone(1);

      commitLatch.countDown();
      update.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      assertSingleCacheEntry();

      TIME_SERVICE.advance(timeout + 2);
      assertSingleCacheEntry();
   }
