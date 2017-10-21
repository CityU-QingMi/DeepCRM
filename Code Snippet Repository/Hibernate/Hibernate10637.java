	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Role) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		Role role = (Role) o;

		if ( name != null ? !name.equals( role.name ) : role.name != null ) {
			return false;
		}

		return true;
	}
