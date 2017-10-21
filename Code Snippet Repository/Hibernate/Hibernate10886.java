	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof NotInsertableTestEntity) ) {
			return false;
		}

		NotInsertableTestEntity that = (NotInsertableTestEntity) o;

		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}
		if ( dataCopy != null ? !dataCopy.equals( that.dataCopy ) : that.dataCopy != null ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
