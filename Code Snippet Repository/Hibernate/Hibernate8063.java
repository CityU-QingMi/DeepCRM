	@Override
	public boolean equals( Object obj ) {
		if (this == obj) {
			return true;
		}
		if ( !( obj instanceof CompositeIdEntity ) ) {
			return false; 
		}
		CompositeIdEntity other = ( CompositeIdEntity ) obj;
		if ( key1 == null ? other.key1 != null : !key1.equals( other.key1 ) ) {
			return false;
		}
		if ( key2 == null ? other.key2 != null : !key2.equals( other.key2 ) ) {
			return false;
		}
		return true;
	}
