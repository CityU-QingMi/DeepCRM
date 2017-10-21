	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Dog ) ) return false;

		final Dog dog = (Dog) o;

		if ( !id.equals( dog.id ) ) return false;

		return true;
	}
