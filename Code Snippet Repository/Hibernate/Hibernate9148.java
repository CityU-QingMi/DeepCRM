	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		Enrolment enrolment = ( Enrolment ) o;

		if ( semester != enrolment.semester ) {
			return false;
		}
		if ( studentNumber != enrolment.studentNumber ) {
			return false;
		}
		if ( year != enrolment.year ) {
			return false;
		}
		if ( courseCode != null ? !courseCode.equals( enrolment.courseCode ) : enrolment.courseCode != null ) {
			return false;
		}

		return true;
	}
