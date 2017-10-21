		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Namespace that = (Namespace) o;
			return this.parts.equals(that.parts);
		}
