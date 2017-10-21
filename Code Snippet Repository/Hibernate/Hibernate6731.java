	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof DogPk ) ) return false;

		final DogPk dogPk = (DogPk) o;

		if ( !name.equals( dogPk.name ) ) return false;
		if ( !ownerName.equals( dogPk.ownerName ) ) return false;

		return true;
	}
