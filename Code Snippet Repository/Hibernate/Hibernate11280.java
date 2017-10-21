	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ExtendedBaseEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		ExtendedBaseEntity that = (ExtendedBaseEntity) o;

		if ( number2 != null ? !number2.equals( that.number2 ) : that.number2 != null ) {
			return false;
		}
		if ( str2 != null ? !str2.equals( that.str2 ) : that.str2 != null ) {
			return false;
		}

		return true;
	}
