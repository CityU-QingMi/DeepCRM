	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Address) ) {
			return false;
		}

		Address address = (Address) o;

		if ( address1 != null ? !address1.equals( address.address1 ) : address.address1 != null ) {
			return false;
		}
		if ( id != null ? !id.equals( address.id ) : address.id != null ) {
			return false;
		}

		return true;
	}
