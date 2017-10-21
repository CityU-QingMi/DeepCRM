	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof NotAuditedDynamicMapComponent ) ) {
			return false;
		}

		NotAuditedDynamicMapComponent that = (NotAuditedDynamicMapComponent) o;

		if ( id != that.id ) {
			return false;
		}
		if ( customFields != null ? !customFields.equals( that.customFields ) : that.customFields != null ) {
			return false;
		}
		if ( note != null ? !note.equals( that.note ) : that.note != null ) {
			return false;
		}

		return true;
	}
