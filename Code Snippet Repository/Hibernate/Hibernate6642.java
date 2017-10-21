	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Footballer ) ) return false;

		final Footballer footballer = (Footballer) o;

		if ( !firstname.equals( footballer.firstname ) ) return false;
		if ( !lastname.equals( footballer.lastname ) ) return false;

		return true;
	}
