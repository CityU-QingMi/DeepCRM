		@Override
		protected void done() {
			if (!isCancelled()) {
				// Implicitly invoked by set/setException: store current thread for
				// determining whether the given result has actually triggered completion
				// (since FutureTask.set/setException unfortunately don't expose that)
				this.completingThread = Thread.currentThread();
			}
			super.done();
		}
