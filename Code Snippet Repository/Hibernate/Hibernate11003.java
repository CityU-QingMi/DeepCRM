	@Override
	public boolean equals(Object object) {
		if ( object == this ) {
			return true;
		}
		if ( !( object instanceof Company) ) {
			return false;
		}
		Company that = (Company) object;
		if ( getId() != null ? !getId().equals( that.getId() ) : that.getId() != null ) {
			return false;
		}
		if ( getName() != null ? !getName().equals( that.getName() ) : that.getName() != null ) {
			return false;
		}
		return true;
	}
