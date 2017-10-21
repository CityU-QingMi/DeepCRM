	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		PK pk = (PK) o;

		return !( id != null ? !id.equals( pk.id ) : pk.id != null );

	}
