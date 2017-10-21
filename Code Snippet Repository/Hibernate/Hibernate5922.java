		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			PersonPK personPK = ( PersonPK ) o;

			if ( firstName != null ? !firstName.equals( personPK.firstName ) : personPK.firstName != null ) {
				return false;
			}
			if ( lastName != null ? !lastName.equals( personPK.lastName ) : personPK.lastName != null ) {
				return false;
			}

			return true;
		}
