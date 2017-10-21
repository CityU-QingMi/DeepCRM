	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof NotAuditedTestEntity) ) {
			return false;
		}

		NotAuditedTestEntity that = (NotAuditedTestEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( str1 != null ? !str1.equals( that.str1 ) : that.str1 != null ) {
			return false;
		}

		return true;
	}
