		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( !(o instanceof PersonTupleId) ) {
				return false;
			}

			PersonTupleId that = (PersonTupleId) o;

			if ( personAId != that.personAId ) {
				return false;
			}
			if ( personBId != that.personBId ) {
				return false;
			}
			if ( constantId != null ? !constantId.equals( that.constantId ) : that.constantId != null ) {
				return false;
			}

			return true;
		}
