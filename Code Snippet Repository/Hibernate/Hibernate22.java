		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			Address address = (Address) o;
			return Objects.equals( street, address.street ) &&
					Objects.equals( number, address.number ) &&
					Objects.equals( postalCode, address.postalCode );
		}
