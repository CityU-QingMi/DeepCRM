	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) {
			return true;
		}
		if ( !(obj instanceof ActivityId) ) {
			return false;
		}
		ActivityId id = (ActivityId) obj;
		return getId().equals( id.getId() ) && getId2().equals( id.getId2() );
	}
