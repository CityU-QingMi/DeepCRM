	public Object nullSafeGet(
			ResultSet aResultSet, String[] names, SharedSessionContractImplementor session, Object aObject
	) throws HibernateException, SQLException {
		Integer highval = StandardBasicTypes.INTEGER.nullSafeGet( aResultSet, names[0], session );
		Integer midval = StandardBasicTypes.INTEGER.nullSafeGet( aResultSet, names[1], session );
		Integer lowval = StandardBasicTypes.INTEGER.nullSafeGet( aResultSet, names[2], session );
		Integer other = StandardBasicTypes.INTEGER.nullSafeGet( aResultSet, names[3], session );

		return new MyOid( highval, midval, lowval, other );
	}
