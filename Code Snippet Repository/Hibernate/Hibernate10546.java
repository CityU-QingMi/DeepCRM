	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof SerObject) ) {
			return false;
		}

		SerObject serObject = (SerObject) o;

		if ( data != null ? !data.equals( serObject.data ) : serObject.data != null ) {
			return false;
		}

		return true;
	}
