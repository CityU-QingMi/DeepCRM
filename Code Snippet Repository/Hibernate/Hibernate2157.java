	public static SQLStateType interpretReportedSQLStateType(int sqlStateType) {
		switch ( sqlStateType ) {
			case DatabaseMetaData.sqlStateSQL99 : {
				return SQL99;
			}
			case DatabaseMetaData.sqlStateXOpen : {
				return XOpen;
			}
			default : {
				return UNKNOWN;
			}
		}
	}
