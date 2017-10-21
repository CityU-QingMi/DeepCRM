   protected Future<Boolean> updateFlushWait(long id, CyclicBarrier loadBarrier, CountDownLatch preFlushLatch, CountDownLatch flushLatch, CountDownLatch commitLatch) throws Exception {
      return executor.submit(() -> withTxSessionApply(s -> {
         try {
            Item item = s.load(Item.class, id);
            item.getName(); // force load & putFromLoad before the barrier
            if (loadBarrier != null) {
               loadBarrier.await(WAIT_TIMEOUT, TimeUnit.SECONDS);
            }
            item.setDescription("Updated item");
            s.update(item);
            if (preFlushLatch != null) {
               awaitOrThrow(preFlushLatch);
            }
            s.flush();
         } catch (OptimisticLockException e) {
            log.info("Exception thrown: ", e);
            markRollbackOnly(s);
            return false;
         } catch (PessimisticLockException | org.hibernate.PessimisticLockException e) {
            log.info("Exception thrown: ", e);
            markRollbackOnly(s);
            return false;
         } finally {
            if (flushLatch != null) {
               flushLatch.countDown();
            }
         }
         if (commitLatch != null) {
            awaitOrThrow(commitLatch);
         }
         return true;
      }));
   }
