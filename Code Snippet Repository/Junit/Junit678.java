		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Segment that = (Segment) o;
			return Objects.equals(this.type, that.type) && Objects.equals(this.value, that.value);
		}
