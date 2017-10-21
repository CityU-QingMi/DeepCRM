   @Test
   public void testPutFromLoadDuringUpdate() throws Exception {
      CountDownLatch flushLatch = new CountDownLatch(1);
      CountDownLatch commitLatch = new CountDownLatch(1);
      CyclicBarrier putFromLoadBarrier = new CyclicBarrier(2);

      // We cannot just do load during update because that could be blocked in DB
      Future<?> putFromLoad = blockedPutFromLoad(putFromLoadBarrier);

      Future<Boolean> update = updateFlushWait(itemId, null, null, flushLatch, commitLatch);
      awaitOrThrow(flushLatch);
      assertTombstone(1);

      unblockPutFromLoad(putFromLoadBarrier, putFromLoad);

      commitLatch.countDown();
      update.get(WAIT_TIMEOUT, TimeUnit.SECONDS);
      assertSingleCacheEntry();
      assertItemDescription("Updated item");
   }
