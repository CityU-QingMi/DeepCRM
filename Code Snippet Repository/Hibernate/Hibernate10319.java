	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof ExtManyToOneNotAuditedNullEntity ) ) return false;
		if ( !super.equals( o ) ) return false;

		ExtManyToOneNotAuditedNullEntity that = (ExtManyToOneNotAuditedNullEntity) o;

		if ( extension != null ? !extension.equals( that.extension ) : that.extension != null ) return false;

		return true;
	}
