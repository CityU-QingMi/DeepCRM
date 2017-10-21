	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !super.equals( obj ) ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		VersionsJoinTableRangeTestAlternateEntity other = (VersionsJoinTableRangeTestAlternateEntity) obj;
		if ( alternateValue == null ) {
			if ( other.alternateValue != null ) {
				return false;
			}
		}
		else if ( !alternateValue.equals( other.alternateValue ) ) {
			return false;
		}
		return true;
	}
