	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Component) ) {
			return false;
		}

		Component that = (Component) o;

		if ( prop2 != that.prop2 ) {
			return false;
		}
		if ( prop1 != null ? !prop1.equals( that.prop1 ) : that.prop1 != null ) {
			return false;
		}

		return true;
	}
