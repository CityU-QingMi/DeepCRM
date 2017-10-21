		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Address address1 = (Address) o;

			if ( getPostalCode() != null ? !getPostalCode().equals( address1.getPostalCode() ) : address1.getPostalCode() != null ) {
				return false;
			}
			if ( getState() != null ? !getState().equals( address1.getState() ) : address1.getState() != null ) {
				return false;
			}
			return getAddress() != null ? getAddress().equals( address1.getAddress() ) : address1.getAddress() == null;
		}
