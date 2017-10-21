		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final EmployeeId other = (EmployeeId) obj;
			if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
				return false;
			}
			return true;
		}
