		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			ConsumerCacheKey otherKey = (ConsumerCacheKey) other;
			return (destinationEquals(otherKey) &&
					ObjectUtils.nullSafeEquals(this.selector, otherKey.selector) &&
					ObjectUtils.nullSafeEquals(this.noLocal, otherKey.noLocal) &&
					ObjectUtils.nullSafeEquals(this.subscription, otherKey.subscription) &&
					this.durable == otherKey.durable);
		}
