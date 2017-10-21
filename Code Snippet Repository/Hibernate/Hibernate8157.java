	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof StateProvince ) ) {
			return false;
		}

		StateProvince that = ( StateProvince ) o;

		if ( isoCode != null ? !isoCode.equals( that.getIsoCode() ) : that.getIsoCode() != null ) {
			return false;
		}
		if ( name != null ? !name.equals( that.getName() ) : that.getName() != null ) {
			return false;
		}

		return true;
	}
