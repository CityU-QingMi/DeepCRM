	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof AuditedSpecialEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		AuditedSpecialEntity that = (AuditedSpecialEntity) o;

		if ( str2 != null ? !str2.equals( that.str2 ) : that.str2 != null ) {
			return false;
		}

		return true;
	}
