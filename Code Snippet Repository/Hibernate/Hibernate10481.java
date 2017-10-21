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
		AbstractCode other = (AbstractCode) obj;
		if ( code != other.code ) {
			return false;
		}
		return true;
	}
