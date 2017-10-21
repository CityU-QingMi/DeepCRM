		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof PropertyCacheKey)) {
				return false;
			}
			PropertyCacheKey otherKey = (PropertyCacheKey) other;
			return (this.clazz == otherKey.clazz && this.property.equals(otherKey.property) &&
					this.targetIsClass == otherKey.targetIsClass);
		}
