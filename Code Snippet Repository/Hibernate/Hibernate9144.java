	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || ! ( o instanceof Course ) ) {
			return false;
		}

		Course course = ( Course ) o;

		if ( courseCode != null ? !courseCode.equals( course.getCourseCode() ) : course.getCourseCode() != null ) {
			return false;
		}
		if ( description != null ? !description.equals( course.getDescription() ) : course.getDescription() != null ) {
			return false;
		}

		return true;
	}
