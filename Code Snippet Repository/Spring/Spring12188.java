		@Override
		public final void onSubscribe(Subscription subscription) {
			this.subscription = subscription;
			if (logger.isDebugEnabled()) {
				logger.debug("Subscribed to Publisher for " + this.emitter);
			}
			this.emitter.onTimeout(() -> {
				if (logger.isDebugEnabled()) {
					logger.debug("Connection timed out for " + this.emitter);
				}
				terminate();
				this.emitter.complete();
			});
			this.emitter.onError(this.emitter::completeWithError);
			subscription.request(1);
		}
