		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o instanceof TestType) {
				TestType other = (TestType) o;
				return this.s.equals(other.s);
			}
			return false;
		}
