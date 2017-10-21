	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		Country country = (Country) o;

		if ( name != null ? !name.equals( country.name ) : country.name != null ) {
			return false;
		}

		return true;
	}
