	public SequenceValueExtractor(Dialect dialect, String sequenceName) {
		this.dialect = dialect;
		if ( dialect instanceof DerbyDialect ) {
			queryString = "VALUES SYSCS_UTIL.SYSCS_PEEK_AT_SEQUENCE('HIBERNATE_ORM_TEST', '" + sequenceName.toUpperCase() + "')";
		}
		else if ( dialect instanceof DB2Dialect ) {
			queryString = "values PREVIOUS value for " + sequenceName;
		}
		else if ( dialect instanceof Oracle8iDialect ) {
			queryString = "select " + sequenceName + ".currval from dual";
		}
		else if ( dialect instanceof SQLServer2012Dialect ) {
			queryString = "SELECT CONVERT(varchar(200), Current_value) FROM sys.sequences WHERE name = '" + sequenceName + "'";
		}
		else if ( dialect instanceof HSQLDialect ) {

			queryString = "call current value for " + sequenceName;
		}
		else if ( dialect instanceof AbstractHANADialect ) {

			queryString = "select " + sequenceName + ".currval from dummy";
		}
		else {
			queryString = "select currval('" + sequenceName + "');";
		}
	}
