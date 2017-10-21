	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj instanceof Membership ) {
			Membership mem = ( Membership ) obj;
			if ( mem.getName() != null && name != null ) {
				return mem.getName().equals( name );
			}
			else {
				return super.equals( obj );
			}
		}
		else {
			return false;
		}
	}
