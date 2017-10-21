   private Future<?> blockedPutFromLoad(CyclicBarrier putFromLoadBarrier) throws InterruptedException, BrokenBarrierException, TimeoutException {
      BlockingInterceptor blockingInterceptor = new BlockingInterceptor(putFromLoadBarrier, PutKeyValueCommand.class, false, true);
      entityCache.addInterceptor(blockingInterceptor, 0);
      cleanup.add(() -> entityCache.removeInterceptor(BlockingInterceptor.class));
      // the putFromLoad should be blocked in the interceptor
      Future<?> putFromLoad = executor.submit(() -> withTxSessionApply(s -> {
         assertEquals("Original item", s.load(Item.class, itemId).getDescription());
         return null;
      }));
      putFromLoadBarrier.await(WAIT_TIMEOUT, TimeUnit.SECONDS);
      blockingInterceptor.suspend(true);
      return putFromLoad;
   }
