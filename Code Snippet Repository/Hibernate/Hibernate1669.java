	@Override
	public String toSqlString(Criteria criteria,CriteriaQuery criteriaQuery) {
		final Dialect dialect = criteriaQuery.getFactory().getDialect();
		final String[] columns = criteriaQuery.findColumns( propertyName, criteria );
		if ( columns.length != 1 ) {
			throw new HibernateException( "Like may only be used with single-column properties" );
		}

		final String escape = escapeChar == null ? "" : " escape \'" + escapeChar + "\'";
		final String column = columns[0];
		if ( ignoreCase ) {
			if ( dialect.supportsCaseInsensitiveLike() ) {
				return column +" " + dialect.getCaseInsensitiveLike() + " ?" + escape;
			}
			else {
				return dialect.getLowercaseFunction() + '(' + column + ')' + " like ?" + escape;
			}
		}
		else {
			return column + " like ?" + escape;
		}
	}
