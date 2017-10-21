		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o instanceof RootElement) {
				RootElement other = (RootElement) o;
				return this.type.equals(other.type);
			}
			return false;
		}
