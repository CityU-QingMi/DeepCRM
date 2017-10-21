	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof AddressEntryPk ) ) return false;

		final AddressEntryPk addressEntryPk = (AddressEntryPk) o;

		if ( !firstname.equals( addressEntryPk.firstname ) ) return false;
		if ( !lastname.equals( addressEntryPk.lastname ) ) return false;

		return true;
	}
