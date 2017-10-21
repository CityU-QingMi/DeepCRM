	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		Address address = ( Address ) o;

		if ( city != null ? !city.equals( address.city ) : address.city != null ) {
			return false;
		}
		if ( country != null ? !country.equals( address.country ) : address.country != null ) {
			return false;
		}
		if ( postalCode != null ? !postalCode.equals( address.postalCode ) : address.postalCode != null ) {
			return false;
		}
		if ( stateProvince != null ? !stateProvince.equals( address.stateProvince ) : address.stateProvince != null ) {
			return false;
		}
		if ( street != null ? !street.equals( address.street ) : address.street != null ) {
			return false;
		}

		return true;
	}
