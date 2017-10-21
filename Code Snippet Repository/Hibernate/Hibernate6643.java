	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof FootballerPk ) ) return false;

		final FootballerPk footballerPk = (FootballerPk) o;

		if ( !firstname.equals( footballerPk.firstname ) ) return false;
		if ( !lastname.equals( footballerPk.lastname ) ) return false;

		return true;
	}
