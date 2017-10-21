	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		final Lotz other = (Lotz) obj;
		if ( this.location == null ) {
			if ( other.location != null ) return false;
		}
		else if ( !this.location.equals( other.location ) ) return false;
		if ( this.lotPK == null ) {
			if ( other.lotPK != null ) return false;
		}
		else if ( !this.lotPK.equals( other.lotPK ) ) return false;
		if ( this.name == null ) {
			if ( other.name != null ) return false;
		}
		else if ( !this.name.equals( other.name ) ) return false;
		return true;
	}
