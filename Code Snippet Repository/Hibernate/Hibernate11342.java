	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Address) ) {
			return false;
		}

		Address address = (Address) o;

		if ( id != address.id ) {
			return false;
		}
		if ( flatNumber != null ? !flatNumber.equals( address.flatNumber ) : address.flatNumber != null ) {
			return false;
		}
		if ( houseNumber != null ? !houseNumber.equals( address.houseNumber ) : address.houseNumber != null ) {
			return false;
		}
		if ( streetName != null ? !streetName.equals( address.streetName ) : address.streetName != null ) {
			return false;
		}

		return true;
	}
