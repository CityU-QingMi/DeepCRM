	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof CustomPropertyAccessRevEntity) ) {
			return false;
		}

		CustomPropertyAccessRevEntity that = (CustomPropertyAccessRevEntity) o;

		if ( customId != that.customId ) {
			return false;
		}
		if ( customTimestamp != that.customTimestamp ) {
			return false;
		}

		return true;
	}
