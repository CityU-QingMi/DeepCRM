	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		CustomRevEntityColumnMapping that = (CustomRevEntityColumnMapping) o;

		if ( customTimestamp != that.customTimestamp ) {
			return false;
		}
		if ( customId != null ? !customId.equals( that.customId ) : that.customId != null ) {
			return false;
		}

		return true;
	}
