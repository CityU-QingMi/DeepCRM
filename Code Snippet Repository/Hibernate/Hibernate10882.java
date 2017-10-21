	@Override
	public boolean equals(Object obj) {
		if ( obj == this ) {
			return true;
		}
		if ( !( obj instanceof Device ) ) {
			return false;
		}
		Device that = (Device) obj;
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		return true;
	}
