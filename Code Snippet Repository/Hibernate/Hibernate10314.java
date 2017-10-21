	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		M2MIndexedListTargetNotAuditedEntity that = (M2MIndexedListTargetNotAuditedEntity) o;

		//noinspection RedundantIfStatement
		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}

		return true;
	}
