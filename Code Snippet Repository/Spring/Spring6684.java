		private MessageConsumer getCachedConsumer(Destination dest, @Nullable String selector,
				@Nullable Boolean noLocal, @Nullable String subscription, boolean durable) throws JMSException {

			ConsumerCacheKey cacheKey = new ConsumerCacheKey(dest, selector, noLocal, subscription, durable);
			MessageConsumer consumer = this.cachedConsumers.get(cacheKey);
			if (consumer != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Found cached JMS MessageConsumer for destination [" + dest + "]: " + consumer);
				}
			}
			else {
				if (dest instanceof Topic) {
					if (noLocal == null) {
						consumer = (durable ?
								this.target.createSharedDurableConsumer((Topic) dest, subscription, selector) :
								this.target.createSharedConsumer((Topic) dest, subscription, selector));
					}
					else {
						consumer = (durable ?
								this.target.createDurableSubscriber((Topic) dest, subscription, selector, noLocal) :
								this.target.createConsumer(dest, selector, noLocal));
					}
				}
				else {
					consumer = this.target.createConsumer(dest, selector);
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Registering cached JMS MessageConsumer for destination [" + dest + "]: " + consumer);
				}
				this.cachedConsumers.put(cacheKey, consumer);
			}
			return new CachedMessageConsumer(consumer);
		}
