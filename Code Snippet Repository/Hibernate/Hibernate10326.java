	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof OneToManyNotAuditedNullEntity ) ) return false;

		OneToManyNotAuditedNullEntity that = (OneToManyNotAuditedNullEntity) o;

		if ( data != null ? !data.equals( that.getData() ) : that.getData() != null ) return false;
		if ( id != null ? !id.equals( that.getId() ) : that.getId() != null ) return false;

		return true;
	}
