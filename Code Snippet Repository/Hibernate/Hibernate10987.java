	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof PersonTuple) ) {
			return false;
		}

		PersonTuple that = (PersonTuple) o;

		return personTupleId.equals( that.personTupleId );
	}
