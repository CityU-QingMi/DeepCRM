	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		NotInsertableEntityType that = (NotInsertableEntityType) o;

		if ( type != null ? !type.equals( that.type ) : that.type != null ) {
			return false;
		}
		if ( typeId != null ? !typeId.equals( that.typeId ) : that.typeId != null ) {
			return false;
		}

		return true;
	}
