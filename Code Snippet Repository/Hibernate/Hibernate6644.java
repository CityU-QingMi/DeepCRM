	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		final Location location = (Location) o;

		if ( Double.compare( location.latitude, latitude ) != 0 ) return false;
		if ( Double.compare( location.longitude, longitude ) != 0 ) return false;

		return true;
	}
