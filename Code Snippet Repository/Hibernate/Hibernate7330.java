	@Override
	public boolean equals(Object obj) {
		log.tracef( "Checking equality : %s -> %s", this, obj );
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !(obj instanceof MapKey) ) {
			return false;
		}
		MapKey other = (MapKey) obj;
		if ( getDefaultValue() == null ) {
			if ( other.getDefaultValue() != null ) {
				return false;
			}
		}
		else if ( !getDefaultValue().equals( other.getDefaultValue() ) ) {
			return false;
		}
		if ( getName() == null ) {
			if ( other.getName() != null ) {
				return false;
			}
		}
		else if ( !getName().equals( other.getName() ) ) {
			return false;
		}
		return true;
	}
