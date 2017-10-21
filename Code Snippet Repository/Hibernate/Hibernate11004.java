	@Override
	public boolean equals(Object object) {
		if ( object == this ) {
			return true;
		}
		if ( !( object instanceof Employee) ) {
			return false;
		}
		Employee that = (Employee) object;
		if ( getId() != null ? !getId().equals( that.getId() ) : that.getId() != null ) {
			return false;
		}
		if ( getName() != null ? !getName().equals( that.getName() ) : that.getName() != null ) {
			return false;
		}
		if ( getCompany() != null ? !getCompany().equals( that.getCompany() ) : that.getCompany() != null ) {
			return false;
		}
		return true;
	}
