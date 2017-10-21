	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof IntTestEntity) ) {
			return false;
		}

		IntTestEntity that = (IntTestEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( number != null ? !number.equals( that.number ) : that.number != null ) {
			return false;
		}

		return true;
	}
