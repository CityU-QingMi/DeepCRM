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
		VersionsJoinTableRangeComponentTestEntity other = (VersionsJoinTableRangeComponentTestEntity) obj;
		if ( component1 == null ) {
			if ( other.component1 != null ) {
				return false;
			}
		}
		else if ( !component1.equals( other.component1 ) ) {
			return false;
		}
		if ( component2 == null ) {
			if ( other.component2 != null ) {
				return false;
			}
		}
		else if ( !component2.equals( other.component2 ) ) {
			return false;
		}
		if ( component3 == null ) {
			if ( other.component3 != null ) {
				return false;
			}
		}
		else if ( !component3.equals( other.component3 ) ) {
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
