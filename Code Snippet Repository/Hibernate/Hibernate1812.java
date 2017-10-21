	private OracleTypesHelper() {
		int typeCode = -99;
		try {
			typeCode = extractOracleCursorTypeValue();
		}
		catch (Exception e) {
			log.warn( "Unable to resolve Oracle CURSOR JDBC type code", e );
		}
		oracleCursorTypeSqlType = typeCode;
	}
