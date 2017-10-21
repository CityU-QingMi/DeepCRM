	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		CustomDateRevEntity that = (CustomDateRevEntity) o;

		if ( customId != that.customId ) {
			return false;
		}
		if ( dateTimestamp != null ? !dateTimestamp.equals( that.dateTimestamp ) : that.dateTimestamp != null ) {
			return false;
		}

		return true;
	}
