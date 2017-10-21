	private void addAuditSecondaryTables(XClass clazz) {
		// Getting information on secondary tables
		final SecondaryAuditTable secondaryVersionsTable1 = clazz.getAnnotation( SecondaryAuditTable.class );
		if ( secondaryVersionsTable1 != null ) {
			auditData.getSecondaryTableDictionary().put(
					secondaryVersionsTable1.secondaryTableName(),
					secondaryVersionsTable1.secondaryAuditTableName()
			);
		}

		final SecondaryAuditTables secondaryAuditTables = clazz.getAnnotation( SecondaryAuditTables.class );
		if ( secondaryAuditTables != null ) {
			for ( SecondaryAuditTable secondaryAuditTable2 : secondaryAuditTables.value() ) {
				auditData.getSecondaryTableDictionary().put(
						secondaryAuditTable2.secondaryTableName(),
						secondaryAuditTable2.secondaryAuditTableName()
				);
			}
		}
	}
