    @Test
    public void testBothType() {
		final SchemaExport schemaExport = new SchemaExport();

        // drop before create (nothing to drop yeT)
        schemaExport.execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.DROP, metadata );
        if ( doesDialectSupportDropTableIfExist() ) {
            assertEquals( 0, schemaExport.getExceptions().size() );
        }
        else {
            assertEquals( 1, schemaExport.getExceptions().size() );
        }

        // drop before create again (this time drops the tables before re-creating)
		schemaExport.execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.BOTH, metadata );
		int exceptionCount = schemaExport.getExceptions().size();
		if ( doesDialectSupportDropTableIfExist() ) {
			assertEquals( 0,  exceptionCount);
		}

		// drop tables
		schemaExport.execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.DROP, metadata );
        assertEquals( 0, schemaExport.getExceptions().size() );
    }
