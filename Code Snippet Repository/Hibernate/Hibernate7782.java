	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof Student ) ) {
			return false;
		}
		Student student = (Student) o;
		return Objects.equals( getFirstName(), student.getFirstName() );
	}
