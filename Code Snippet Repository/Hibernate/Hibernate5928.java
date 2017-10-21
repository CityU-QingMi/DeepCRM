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
		ProductAttributeId other = (ProductAttributeId) obj;
		if ( key == null ) {
			if ( other.getKey() != null ) {
				return false;
			}
		}
		else if ( !key.equals( other.getKey() ) ) {
			return false;
		}
		if ( owner == null ) {
			if ( other.getOwner() != null ) {
				return false;
			}
		}
		else if ( !owner.equals( other.getOwner() ) ) {
			return false;
		}
		return true;
	}
