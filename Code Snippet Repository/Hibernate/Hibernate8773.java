	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj instanceof  Group ) {
			Group grp = ( Group ) obj;
			if ( grp.getName() != null && name != null ) {
				return grp.getName().equals( name );
			}
			else {
				return super.equals( obj );
			}
		}
		else {
			return false;
		}
	}
