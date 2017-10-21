	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof ParentPk ) ) return false;

		final ParentPk parentPk = (ParentPk) o;

		if ( !firstName.equals( parentPk.firstName ) ) return false;
		if ( !lastName.equals( parentPk.lastName ) ) return false;

		return true;
	}
