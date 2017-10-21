	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		ClassDescriptorImpl that = (ClassDescriptorImpl) o;
		return name.equals( that.name );
	}
