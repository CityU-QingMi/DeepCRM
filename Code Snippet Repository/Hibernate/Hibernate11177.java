	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof CustomBoxedRevEntity) ) {
			return false;
		}

		CustomBoxedRevEntity that = (CustomBoxedRevEntity) o;

		if ( customId != null ? !customId.equals( that.customId ) : that.customId != null ) {
			return false;
		}
		if ( customTimestamp != null ?
				!customTimestamp.equals( that.customTimestamp ) :
				that.customTimestamp != null ) {
			return false;
		}

		return true;
	}
