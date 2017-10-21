	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		NamedParameterDescriptor that = (NamedParameterDescriptor) o;
		return getName().equals( that.getName() );
	}
