      @Override
      public Object visitPutKeyValueCommand(InvocationContext ctx, PutKeyValueCommand command) throws Throwable {
         if (command.hasFlag(Flag.ZERO_LOCK_ACQUISITION_TIMEOUT)) {
            if (firstPutFromLoad.compareAndSet(true, false)) {
               updateLatch.countDown();
               putFromLoadLatch.await();
            }
         }
         return super.visitPutKeyValueCommand(ctx, command);
      }
