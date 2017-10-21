	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof AnnotatedTrackingRevisionEntity) ) {
			return false;
		}

		AnnotatedTrackingRevisionEntity that = (AnnotatedTrackingRevisionEntity) o;

		if ( customId != that.customId ) {
			return false;
		}
		if ( customTimestamp != that.customTimestamp ) {
			return false;
		}
		if ( entityNames != null ? !entityNames.equals( that.entityNames ) : that.entityNames != null ) {
			return false;
		}

		return true;
	}
