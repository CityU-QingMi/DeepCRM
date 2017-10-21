	@Override
	public boolean equals(Object object) {
		if ( this == object ) {
			return true;
		}
		if ( object == null || getClass() != object.getClass() ) {
			return false;
		}

		Type that = (Type) object;
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		return !( name != null ? !name.equals( that.name ) : that.name != null );
	}
