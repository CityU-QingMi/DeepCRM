	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof UnversionedStrTestEntity) ) {
			return false;
		}

		UnversionedStrTestEntity that = (UnversionedStrTestEntity) o;

		if ( id != null ? !id.equals( that.getId() ) : that.getId() != null ) {
			return false;
		}
		if ( str != null ? !str.equals( that.getStr() ) : that.getStr() != null ) {
			return false;
		}

		return true;
	}
