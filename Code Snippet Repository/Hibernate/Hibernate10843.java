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
		VersionsJoinTableRangeComponent<?> other = (VersionsJoinTableRangeComponent<?>) obj;
		if ( range == null ) {
			if ( other.range != null ) {
				return false;
			}
		}
		else if ( !range.equals( other.range ) ) {
			return false;
		}
		return true;
	}
