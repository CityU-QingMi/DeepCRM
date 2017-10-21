	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !(obj instanceof DoesNotWork) ) {
			return false;
		}
		DoesNotWork other = (DoesNotWork) obj;
		if ( doesNotWorkPk == null ) {
			if ( other.doesNotWorkPk != null ) {
				return false;
			}
		}
		else if ( !doesNotWorkPk.equals( other.doesNotWorkPk ) ) {
			return false;
		}
		return true;
	}
