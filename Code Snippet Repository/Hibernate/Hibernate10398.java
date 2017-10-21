	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ScalePrecisionEntity) ) {
			return false;
		}

		ScalePrecisionEntity that = (ScalePrecisionEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( wholeNumber != null ? !wholeNumber.equals( that.wholeNumber ) : that.wholeNumber != null ) {
			return false;
		}

		return true;
	}
