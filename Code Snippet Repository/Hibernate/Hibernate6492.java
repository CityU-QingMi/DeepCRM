	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !( obj instanceof ContactType ) ) {
			return false;
		}
		ContactType other = (ContactType) obj;
		if ( id != null ) {
			if ( !id.equals( other.id ) ) {
				return false;
			}
		}
		return true;
	}
