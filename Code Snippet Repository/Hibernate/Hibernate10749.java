	@Test
	public void testJoinTableForeignKeyToNonAuditTables() {
		// there should only be references to REVINFO and not to the Customer or Address tables
		for ( Table table : metadata().getDatabase().getDefaultNamespace().getTables() ) {
			if ( table.getName().equals( "CustomerAddress_AUD" ) ) {
				for ( org.hibernate.mapping.ForeignKey foreignKey : table.getForeignKeys().values() ) {
					assertEquals( "REVINFO", foreignKey.getReferencedTable().getName() );
				}
			}
		}
	}
