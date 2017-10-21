	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || ! ( o instanceof Student ) ) {
			return false;
		}

		Student student = ( Student ) o;

		if ( studentNumber != student.getStudentNumber() ) {
			return false;
		}
		if ( name != null ? !name.equals( student.getName() ) : student.getName() != null ) {
			return false;
		}

		return true;
	}
