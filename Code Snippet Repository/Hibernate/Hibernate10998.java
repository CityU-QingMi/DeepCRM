	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof OneToManyOwned ) ) {
			return false;
		}

		OneToManyOwned that = (OneToManyOwned) o;
		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}
		return true;
	}
