	@Override
	public boolean equals(Object obj) {
		if ( obj == this ) {
			return true;
		}
		if ( !(obj instanceof NormalActivity) ) {
			return false;
		}
		NormalActivity normalActivity = (NormalActivity) obj;
		return getId().equals( normalActivity.getId() );
	}
