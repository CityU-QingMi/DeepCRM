	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof House ) ) return false;

		final House house = (House) o;

		if ( address != null ? !address.equals( house.address ) : house.address != null ) return false;

		return true;
	}
