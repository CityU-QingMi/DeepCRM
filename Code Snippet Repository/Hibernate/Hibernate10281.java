	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof EnumTypeEntity) ) {
			return false;
		}

		EnumTypeEntity that = (EnumTypeEntity) o;

		if ( enum1 != that.enum1 ) {
			return false;
		}
		if ( enum2 != that.enum2 ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
