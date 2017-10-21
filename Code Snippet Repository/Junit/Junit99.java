		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			CompositeKey that = (CompositeKey) o;
			return this.namespace.equals(that.namespace) && this.key.equals(that.key);
		}
