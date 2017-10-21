   protected Future<Boolean> evictWait(long id, CyclicBarrier loadBarrier, CountDownLatch preEvictLatch, CountDownLatch postEvictLatch) throws Exception {
      return executor.submit(() -> {
         try {
            loadBarrier.await(WAIT_TIMEOUT, TimeUnit.SECONDS);
            if (preEvictLatch != null) {
               awaitOrThrow(preEvictLatch);
            }
            sessionFactory().getCache().evictEntity(Item.class, id);
         } finally {
            if (postEvictLatch != null) {
               postEvictLatch.countDown();
            }
         }
         return true;
      });
   }
