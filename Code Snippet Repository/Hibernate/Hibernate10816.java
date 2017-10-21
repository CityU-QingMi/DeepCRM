	@Override
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
		if ( city != null ? !city.equals( address.city ) : address.city != null ) {
			return false;
		}

		return true;
	}
