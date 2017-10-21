		private void schedule() {
			try {
				this.taskExecutor.execute(this);
			}
			catch (Throwable ex) {
				try {
					terminate();
				}
				finally {
					this.executing.decrementAndGet();
					this.elementRef.lazySet(null);
				}
			}
		}
