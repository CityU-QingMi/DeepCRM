	private boolean checkDropConstraint(String tableName, String columnName) throws IOException {
		boolean matches = false;
		String regex = getDialect().getAlterTableString( tableName ) + " drop constraint";

		if ( getDialect().supportsIfExistsBeforeConstraintName() ) {
			regex += " if exists";
		}
		regex += " uk_(.)*";
		if ( getDialect().supportsIfExistsAfterConstraintName() ) {
			regex += " if exists";
		}

		return isMatching( matches, regex );
	}
