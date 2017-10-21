	public Object getPropertyValue(Object aObject, int i) throws HibernateException {
		MyOid dbOid = (MyOid) aObject;
		switch ( i ) {
			case 0:
				return dbOid.getHigh();
			case 1:
				return dbOid.getMiddle();
			case 2:
				return dbOid.getLow();
			case 3:
				return dbOid.getOther();
			default:
				throw new HibernateException( "Unsupported property index " + i );
		}

	}
