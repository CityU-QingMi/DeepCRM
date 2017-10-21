		@Override
		public Object visitGetKeyValueCommand(InvocationContext ctx, GetKeyValueCommand command) throws Throwable {
			try {
				Phaser phaser;
				Thread thread;
				synchronized (this) {
					phaser = this.phaser;
					thread = this.thread;
				}
				if (phaser != null && Thread.currentThread() == thread) {
					arriveAndAwait(phaser);
					arriveAndAwait(phaser);
				}
			} catch (Exception e) {
				failure.set(e);
				throw e;
			} finally {
				return super.visitGetKeyValueCommand(ctx, command);
			}
		}
