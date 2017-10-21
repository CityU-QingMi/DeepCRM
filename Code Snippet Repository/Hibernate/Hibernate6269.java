	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Ticket ) ) return false;

		final Ticket ticket = (Ticket) o;

		if ( !number.equals( ticket.number ) ) return false;

		return true;
	}
