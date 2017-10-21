	public void nullSafeSet(
			PreparedStatement aPreparedStatement, Object value, int index, SharedSessionContractImplementor session
	) throws HibernateException, SQLException {
		MyOid c;
		if ( value == null ) {
			// todo is this correct?
			throw new HibernateException( "Oid object may not be null" );
		}
		else {
			c = (MyOid) value;
		}

		StandardBasicTypes.INTEGER.nullSafeSet( aPreparedStatement, c.getHigh(), index, session );
		StandardBasicTypes.INTEGER.nullSafeSet( aPreparedStatement, c.getMiddle(), index + 1, session );
		StandardBasicTypes.INTEGER.nullSafeSet( aPreparedStatement, c.getLow(), index + 2, session );
		StandardBasicTypes.INTEGER.nullSafeSet( aPreparedStatement, c.getOther(), index + 3, session );
	}
