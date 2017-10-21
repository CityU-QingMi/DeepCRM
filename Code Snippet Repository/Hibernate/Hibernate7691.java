	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( ! ( o instanceof UnspecifiedEnumTypeEntity ) ) return false;

		UnspecifiedEnumTypeEntity that = (UnspecifiedEnumTypeEntity) o;

		if ( enum1 != that.enum1 ) return false;
		if ( enum2 != that.enum2 ) return false;
		if ( id != null ? !id.equals( that.id ) : that.id != null ) return false;

		return true;
	}
