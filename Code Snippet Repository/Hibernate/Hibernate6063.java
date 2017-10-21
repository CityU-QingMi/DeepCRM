	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Contractor ) ) return false;
		if ( !super.equals( o ) ) return false;

		Contractor that = (Contractor) o;

		if ( company != null ? !company.equals( that.company ) : that.company != null ) return false;

		return true;
	}
