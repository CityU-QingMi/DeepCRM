	@Override
	public boolean equals(Object object) {
		if ( this == object ) {
			return true;
		}
		if ( object == null || !( object instanceof AuditedEntity ) ) {
			return false;
		}

		AuditedEntity that = (AuditedEntity) object;
		return !( name != null ? !name.equals( that.name ) : that.name != null );
	}
