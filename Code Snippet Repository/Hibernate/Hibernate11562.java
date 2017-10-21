      @Override
      public Object visitGetKeyValueCommand(InvocationContext ctx, GetKeyValueCommand command) throws Throwable {
         Phaser phaser;
         Thread thread;
         synchronized (this) {
            phaser = this.phaser;
            thread = this.thread;
         }
         if (phaser != null && Thread.currentThread() == thread) {
            arriveAndAwait(phaser, 2000);
            arriveAndAwait(phaser, 2000);
         }
         return super.visitGetKeyValueCommand(ctx, command);
      }
