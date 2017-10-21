	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Inhabitant ) ) return false;

		final Inhabitant inhabitant = (Inhabitant) o;

		if ( name != null ? !name.equals( inhabitant.name ) : inhabitant.name != null ) return false;

		return true;
	}
