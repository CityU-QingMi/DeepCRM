	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		CourseMeetingId that = ( CourseMeetingId ) o;

		if ( period != that.period ) {
			return false;
		}
		if ( courseCode != null ? !courseCode.equals( that.courseCode ) : that.courseCode != null ) {
			return false;
		}
		if ( day != null ? !day.equals( that.day ) : that.day != null ) {
			return false;
		}
		if ( location != null ? !location.equals( that.location ) : that.location != null ) {
			return false;
		}

		return true;
	}
