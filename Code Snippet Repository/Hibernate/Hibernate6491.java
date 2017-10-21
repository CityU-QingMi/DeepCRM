	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		ContactInformation that = (ContactInformation) o;
		return Objects.equals( name, that.name );
	}
