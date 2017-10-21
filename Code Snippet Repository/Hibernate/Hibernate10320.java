	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof ManyToOneNotAuditedNullEntity ) ) return false;

		ManyToOneNotAuditedNullEntity that = (ManyToOneNotAuditedNullEntity) o;

		if ( data != null ? !data.equals( that.getData() ) : that.getData() != null ) return false;
		if ( id != null ? !id.equals( that.getId() ) : that.getId() != null ) return false;

		return true;
	}
