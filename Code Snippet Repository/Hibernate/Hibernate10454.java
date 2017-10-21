	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Name) ) {
			return false;
		}

		Name name = (Name) o;
		if ( firstName != null ? !firstName.equals( name.firstName ) : name.firstName != null ) {
			return false;
		}
		if ( lastName != null ? !lastName.equals( name.lastName ) : name.lastName != null ) {
			return false;
		}

		return true;
	}
