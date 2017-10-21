	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !(obj instanceof DoesNotWorkPk) ) {
			return false;
		}
		DoesNotWorkPk other = (DoesNotWorkPk) obj;
		if ( id1 == null ) {
			if ( other.id1 != null ) {
				return false;
			}
		}
		else if ( !id1.equals( other.id1 ) ) {
			return false;
		}
		if ( id2 == null ) {
			if ( other.id2 != null ) {
				return false;
			}
		}
		else if ( !id2.equals( other.id2 ) ) {
			return false;
		}
		return true;
	}
