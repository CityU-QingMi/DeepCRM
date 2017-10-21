	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof RightsSubject) ) {
			return false;
		}

		RightsSubject that = (RightsSubject) o;

		if ( group != null ? !group.equals( that.group ) : that.group != null ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
