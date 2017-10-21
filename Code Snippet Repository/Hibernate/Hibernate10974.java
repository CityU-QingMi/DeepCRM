	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Constant) ) {
			return false;
		}

		Constant constant = (Constant) o;

		if ( id != null ? !id.equals( constant.id ) : constant.id != null ) {
			return false;
		}
		if ( name != null ? !name.equals( constant.name ) : constant.name != null ) {
			return false;
		}

		return true;
	}
