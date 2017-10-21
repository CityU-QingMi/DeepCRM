	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof PropertyOverrideEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		PropertyOverrideEntity that = (PropertyOverrideEntity) o;

		if ( str2 != null ? !str2.equals( that.str2 ) : that.str2 != null ) {
			return false;
		}

		return true;
	}
