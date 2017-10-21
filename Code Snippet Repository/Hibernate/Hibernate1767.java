	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		// Hibernate uses this information for temporary tables that it uses for its own operations
		// therefore the appropriate strategy is taken with different versions of HSQLDB

		// All versions of HSQLDB support GLOBAL TEMPORARY tables where the table
		// definition is shared by all users but data is private to the session
		// HSQLDB 2.0 also supports session-based LOCAL TEMPORARY tables where
		// the definition and data is private to the session and table declaration
		// can happen in the middle of a transaction

		if ( hsqldbVersion < 200 ) {
			return new GlobalTemporaryTableBulkIdStrategy(
					new IdTableSupportStandardImpl() {
						@Override
						public String generateIdTableName(String baseName) {
							return "HT_" + baseName;
						}

						@Override
						public String getCreateIdTableCommand() {
							return "create global temporary table";
						}
					},
					// Version 1.8 GLOBAL TEMPORARY table definitions persist beyond the end
					// of the session (by default, data is cleared at commit).
					AfterUseAction.CLEAN
			);
		}
		else {
			return new LocalTemporaryTableBulkIdStrategy(
					new IdTableSupportStandardImpl() {
						@Override
						public String generateIdTableName(String baseName) {
							// With HSQLDB 2.0, the table name is qualified with MODULE to assist the drop
							// statement (in-case there is a global name beginning with HT_)
							return "MODULE.HT_" + baseName;
						}

						@Override
						public String getCreateIdTableCommand() {
							return "declare local temporary table";
						}
					},
					AfterUseAction.DROP,
					TempTableDdlTransactionHandling.NONE
			);
		}
	}
