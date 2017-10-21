	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		StudentDTO that = ( StudentDTO ) o;

		if ( courseDescription != null ? !courseDescription.equals( that.courseDescription ) : that.courseDescription != null ) {
			return false;
		}
		if ( studentName != null ? !studentName.equals( that.studentName ) : that.studentName != null ) {
			return false;
		}

		return true;
	}
