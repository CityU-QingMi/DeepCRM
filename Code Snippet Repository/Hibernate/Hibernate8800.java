	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( ( o == null ) || ( getClass() != o.getClass() ) ) {
			return false;
		}
		LocalizedValue that = ( (LocalizedValue) o );
		return Objects.equals( value, that.value );
	}
