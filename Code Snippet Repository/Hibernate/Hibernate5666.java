	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if ( x == y )
			return true;
		if ( x == null || y == null )
			return false;
		Date dx = (Date) x;
		Date dy = (Date) y;

		return dx.equals( dy );
	}
