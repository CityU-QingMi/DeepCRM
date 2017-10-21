		@Override
		public void run() {
			if (this.done) {
				this.elementRef.lazySet(null);
				return;
			}

			// Check terminal signal before processing element..
			boolean isTerminated = this.terminated;

			Object element = this.elementRef.get();
			if (element != null) {
				this.elementRef.lazySet(null);
				Assert.state(this.subscription != null, "No subscription");
				try {
					send(element);
					this.subscription.request(1);
				}
				catch (final Throwable ex) {
					if (logger.isDebugEnabled()) {
						logger.debug("Send error for " + this.emitter, ex);
					}
					terminate();
					return;
				}
			}
			
			if (isTerminated) {
				this.done = true;
				Throwable ex = this.error;
				this.error = null;
				if (ex != null) {
					if (logger.isDebugEnabled()) {
						logger.debug("Publisher error for " + this.emitter, ex);
					}
					this.emitter.completeWithError(ex);
				}
				else {
					if (logger.isDebugEnabled()) {
						logger.debug("Publishing completed for " + this.emitter);
					}
					this.emitter.complete();
				}
				return;
			}
			
			if (this.executing.decrementAndGet() != 0) {
				schedule();
			}
		}
