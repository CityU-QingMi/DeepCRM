	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Luggage ) ) return false;

		final Luggage luggage = (Luggage) o;

		if ( !owner.equals( luggage.owner ) ) return false;
		if ( !type.equals( luggage.type ) ) return false;

		return true;
	}
