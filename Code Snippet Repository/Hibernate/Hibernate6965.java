	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		final SocialSecurityNumber that = (SocialSecurityNumber) o;

		if ( !countryCode.equals( that.countryCode ) ) return false;
		if ( !number.equals( that.number ) ) return false;

		return true;
	}
