	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !( obj instanceof Person ) ) {
			return false;
		}
		Person that = (Person) obj;
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( name != null ? !name.equals( that.name) : that.name != null ) {
			return false;
		}
		if ( nameInfo != null ? !nameInfo.equals( that.nameInfo ) : that.nameInfo != null ) {
			return false;
		}
		return true;
	}
