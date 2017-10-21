   protected Future<Boolean> removeFlushWait(long id, CyclicBarrier loadBarrier, CountDownLatch preFlushLatch, CountDownLatch flushLatch, CountDownLatch commitLatch) throws Exception {
      return executor.submit(() -> withTxSessionApply(s -> {
         try {
            Item item = s.load(Item.class, id);
            item.getName(); // force load & putFromLoad before the barrier
            loadBarrier.await(WAIT_TIMEOUT, TimeUnit.SECONDS);
            s.delete(item);
            if (preFlushLatch != null) {
               awaitOrThrow(preFlushLatch);
            }
            s.flush();
         } catch (OptimisticLockException e) {
            log.info("Exception thrown: ", e);
            markRollbackOnly(s);
            return false;
         } catch (PessimisticLockException e) {
            log.info("Exception thrown: ", e);
            markRollbackOnly(s);
            return false;
         } finally {
            if (flushLatch != null) {
               flushLatch.countDown();
            }
         }
         awaitOrThrow(commitLatch);
         return true;
      }));
   }
