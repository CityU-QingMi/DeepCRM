	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !(obj instanceof Component4) ) {
			return false;
		}

		Component4 other = (Component4) obj;

		if ( description != null ? !description.equals( other.description ) : other.description != null ) {
			return false;
		}
		if ( key != null ? !key.equals( other.key ) : other.key != null ) {
			return false;
		}
		if ( value != null ? !value.equals( other.value ) : other.value != null ) {
			return false;
		}

		return true;
	}
