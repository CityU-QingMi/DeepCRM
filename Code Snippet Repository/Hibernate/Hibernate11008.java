	@Override
	public boolean equals(Object object) {
		if ( object == this ) {
			return true;
		}
		if ( !( object instanceof BiRefingOptionalEntity ) ) {
			return false;
		}
		BiRefingOptionalEntity that = (BiRefingOptionalEntity) object;
		return !( id != null ? !id.equals( that.id ) : that.id != null );
	}
