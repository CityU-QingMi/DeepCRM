    @Test
    public void testCreateAndDrop() {
		final SchemaExport schemaExport = new SchemaExport();

        // should drop before creating, but tables don't exist yet
        schemaExport.create( EnumSet.of( TargetType.DATABASE ), metadata );
		if ( doesDialectSupportDropTableIfExist() ) {
			assertEquals( 0, schemaExport.getExceptions().size() );
		}
		else {
			assertEquals( 1, schemaExport.getExceptions().size() );
		}
        // call create again; it should drop tables before re-creating
		schemaExport.create( EnumSet.of( TargetType.DATABASE ), metadata );
        assertEquals( 0, schemaExport.getExceptions().size() );
        // drop the tables
		schemaExport.drop( EnumSet.of( TargetType.DATABASE ), metadata );
        assertEquals( 0, schemaExport.getExceptions().size() );
    }
