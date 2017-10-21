	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		MapKey mapKey = ( MapKey ) o;

		if ( !role.equals( mapKey.role ) ) {
			return false;
		}

		return true;
	}
