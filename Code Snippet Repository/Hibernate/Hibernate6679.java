	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Dress ) ) return false;

		final Dress dress = (Dress) o;

		if ( !getId().equals( dress.getId() ) ) return false;

		return true;
	}
