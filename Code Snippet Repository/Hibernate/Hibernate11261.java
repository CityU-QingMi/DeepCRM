	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof AuditedAllMappedSuperclass) ) {
			return false;
		}

		AuditedAllMappedSuperclass that = (AuditedAllMappedSuperclass) o;

		if ( str != null ? !str.equals( that.str ) : that.str != null ) {
			return false;
		}

		return true;
	}
