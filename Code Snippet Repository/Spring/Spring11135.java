		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o instanceof Credentials) {
				Credentials other = (Credentials) o;
				return this.username.equals(other.username) &&
						this.password.equals(other.password);

			}
			return false;
		}
