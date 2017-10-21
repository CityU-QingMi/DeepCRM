	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		VersionsJoinTableRangeTestEntitySuperClass other = (VersionsJoinTableRangeTestEntitySuperClass) obj;
		if ( genericValue == null ) {
			if ( other.genericValue != null ) {
				return false;
			}
		}
		else if ( !genericValue.equals( other.genericValue ) ) {
			return false;
		}
		if ( id == null ) {
			if ( other.id != null ) {
				return false;
			}
		}
		else if ( !id.equals( other.id ) ) {
			return false;
		}
		return true;
	}
