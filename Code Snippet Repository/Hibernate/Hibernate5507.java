	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Distributor ) ) return false;

		final Distributor distributor = (Distributor) o;

		if ( !name.equals( distributor.name ) ) return false;

		return true;
	}
