	public void setPropertyValue(Object aObject, int i, Object aObject1) throws HibernateException {
		MyOid dbOid = (MyOid) aObject;
		switch ( i ) {
			case 0:
				dbOid.setHigh( (Integer) aObject1 );
			case 1:
				dbOid.setMiddle( (Integer) aObject1 );
			case 2:
				dbOid.setLow( (Integer) aObject1 );
			case 3:
				dbOid.setOther( (Integer) aObject1 );
			default:
				throw new HibernateException( "Unsupported property index " + i );
		}
	}
