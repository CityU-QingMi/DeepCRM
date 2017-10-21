	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		Child child = ( Child ) o;

		if ( description != null ? ! description.equals( child.description ) : child.description != null ) {
			return false;
		}
		if ( ! name.equals( child.name ) ) {
			return false;
		}

		return true;
	}
