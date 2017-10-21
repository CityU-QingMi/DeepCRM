	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		final FromEntity other = (FromEntity) obj;
		if ( ( this.name == null ) ? ( other.name != null ) : !this.name.equals( other.name ) ) {
			return false;
		}
		if ( ( this.lastName == null ) ? ( other.lastName != null ) : !this.lastName.equals( other.lastName ) ) {
			return false;
		}
		return true;
	}
