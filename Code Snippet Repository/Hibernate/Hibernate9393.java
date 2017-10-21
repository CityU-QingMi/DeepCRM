	private boolean checkDropIndex(String tableName, String columnName) throws IOException {
		boolean matches = false;
		String regex = "alter table " + tableName + " drop index";

		if ( getDialect().supportsIfExistsBeforeConstraintName() ) {
			regex += " if exists";
		}
		regex += " uk_(.)*";
		if ( getDialect().supportsIfExistsAfterConstraintName() ) {
			regex += " if exists";
		}

		return isMatching( matches, regex );
	}
