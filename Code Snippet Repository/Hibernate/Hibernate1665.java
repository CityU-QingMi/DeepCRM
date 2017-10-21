	@Override
	public String toSqlString( Criteria criteria, CriteriaQuery criteriaQuery ) {
		final String[] columns = criteriaQuery.findColumns( propertyName, criteria );
		if ( criteriaQuery.getFactory().getDialect().supportsRowValueConstructorSyntaxInInList() || columns.length <= 1 ) {
			String singleValueParam = StringHelper.repeat( "?, ", columns.length - 1 ) + "?";
			if ( columns.length > 1 ) {
				singleValueParam = '(' + singleValueParam + ')';
			}
			final String params = values.length > 0
					? StringHelper.repeat( singleValueParam + ", ", values.length - 1 ) + singleValueParam
					: "";
			String cols = StringHelper.join( ", ", columns );
			if ( columns.length > 1 ) {
				cols = '(' + cols + ')';
			}
			return cols + " in (" + params + ')';
		}
		else {
			String cols = " ( " + StringHelper.join( " = ? and ", columns ) + "= ? ) ";
			cols = values.length > 0
					? StringHelper.repeat( cols + "or ", values.length - 1 ) + cols
					: "";
			cols = " ( " + cols + " ) ";
			return cols;
		}
	}
