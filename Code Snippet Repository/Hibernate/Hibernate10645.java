	@Override
	public boolean equals(Object obj) {
		if ( obj == this ) {
			return true;
		}
		if ( !(obj instanceof CheckInActivity) ) {
			return false;
		}
		CheckInActivity checkInActivity = (CheckInActivity) obj;
		return getId().equals( checkInActivity.getId() );
	}
