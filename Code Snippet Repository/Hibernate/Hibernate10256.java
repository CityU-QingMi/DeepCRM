	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !(obj instanceof Component3) ) {
			return false;
		}

		Component3 other = (Component3) obj;

		if ( auditedComponent != null ?
				!auditedComponent.equals( other.auditedComponent ) :
				other.auditedComponent != null ) {
			return false;
		}
		if ( str1 != null ? !str1.equals( other.str1 ) : other.str1 != null ) {
			return false;
		}

		return true;
	}
