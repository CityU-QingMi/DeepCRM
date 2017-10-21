	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof AddressEntry ) ) return false;

		final AddressEntry addressEntry = (AddressEntry) o;

		if ( !person.equals( addressEntry.person ) ) return false;

		return true;
	}
