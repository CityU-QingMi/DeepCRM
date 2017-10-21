	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj instanceof  User ) {
			User user = ( User ) obj;
			if ( user.getName() != null && name != null ) {
				return user.getName().equals( name );
			}
			else {
				return super.equals( obj );
			}
		}
		else {
			return false;
		}
	}
