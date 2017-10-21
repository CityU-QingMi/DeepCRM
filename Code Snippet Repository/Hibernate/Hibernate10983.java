		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Design design = (Design) o;

			return id != null ? id.equals( design.id ) : design.id == null;
		}
