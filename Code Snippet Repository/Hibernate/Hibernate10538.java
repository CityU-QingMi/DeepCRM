	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof DateTestEntity) ) {
			return false;
		}

		DateTestEntity that = (DateTestEntity) o;

		if ( dateValue != null ) {
			if ( that.dateValue == null ) {
				return false;
			}

			if ( dateValue.getTime() != that.dateValue.getTime() ) {
				return false;
			}
		}

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
