	@Override
	public boolean equals(Object obj) {
		if ( obj == this ) {
			return true;
		}
		if ( !( obj instanceof NameInfo ) ) {
			return false;
		}
		NameInfo that = (NameInfo) obj;
		if ( firstName != null ? !firstName.equals( that.firstName) : that.firstName != null ) {
			return false;
		}
		if ( lastName != null ? !lastName.equals( that.lastName) : that.lastName != null ) {
			return false;
		}
		return true;
	}
