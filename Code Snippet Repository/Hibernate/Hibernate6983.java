	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		final MyOid myOid = (MyOid) o;

		if ( high != myOid.high ) return false;
		if ( low != myOid.low ) return false;
		if ( middle != myOid.middle ) return false;
		if ( other != myOid.other ) return false;

		return true;
	}
