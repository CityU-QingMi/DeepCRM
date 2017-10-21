	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Building other = (Building) obj;
		if ( address == null ) {
			if ( other.address != null )
				return false;
		}
		else if ( !address.equals( other.address ) )
			return false;
		if ( city == null ) {
			if ( other.city != null )
				return false;
		}
		else if ( !city.equals( other.city ) )
			return false;
		if ( state == null ) {
			if ( other.state != null )
				return false;
		}
		else if ( !state.equals( other.state ) )
			return false;
		return true;
	}
