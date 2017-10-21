	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Cat ) ) return false;

		final Cat cat = (Cat) o;

		if ( !id.equals( cat.id ) ) return false;

		return true;
	}
