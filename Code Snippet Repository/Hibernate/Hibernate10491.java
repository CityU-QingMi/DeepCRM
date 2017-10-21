	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof InternalComponent ) ) {
			return false;
		}

		InternalComponent that = (InternalComponent) o;

		if ( property != null ? !property.equals( that.property ) : that.property != null ) {
			return false;
		}

		return true;
	}
