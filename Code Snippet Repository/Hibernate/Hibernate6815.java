	public boolean equals(Object other) {
		if ( this == other )
			return true;
		if ( ( other == null ) || !( other.getClass().equals( this.getClass() ) ) )
			return false;
		GenericObject anObject = (GenericObject) other;
		if ( this.id == 0 || anObject.id == 0 )
			return false;

		return ( this.id == anObject.id );
	}
