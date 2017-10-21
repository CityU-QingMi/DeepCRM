	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Computer ) ) return false;

		final Computer computer = (Computer) o;

		if ( !id.equals( computer.id ) ) return false;

		return true;
	}
