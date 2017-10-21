	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof TransitiveOverrideEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		TransitiveOverrideEntity that = (TransitiveOverrideEntity) o;

		if ( str3 != null ? !str3.equals( that.str3 ) : that.str3 != null ) {
			return false;
		}

		return true;
	}
