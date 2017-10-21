		@Override
		public String toString() {
			// @formatter:off
			return new ToStringBuilder(this)
					.append("displayName", this.displayName)
					.append("tags", this.tags)
					.append("testClass", nullSafeGet(this.testClass))
					.append("testMethod", nullSafeGet(this.testMethod))
					.toString();
			// @formatter:on
		}
