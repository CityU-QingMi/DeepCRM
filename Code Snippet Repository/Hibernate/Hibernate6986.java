	public boolean equals(Object x, Object y) throws HibernateException {
		if ( x == y ) return true;
		if ( x == null || y == null ) return false;

		MyOid oid1 = (MyOid) x;
		MyOid oid2 = (MyOid) y;

		if ( oid1.getHigh() != oid2.getHigh() ) {
			return false;
		}
		if ( oid1.getMiddle() != oid2.getMiddle() ) {
			return false;
		}
		if ( oid1.getLow() != oid2.getLow() ) {
			return false;
		}
		return oid1.getOther() == oid2.getOther();

	}
