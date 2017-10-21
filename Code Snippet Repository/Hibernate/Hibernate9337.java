    @Test
    public void testCreateAndDropOnlyType() {
		final SchemaExport schemaExport = new SchemaExport();

        // create w/o dropping first; (OK because tables don't exist yet
        schemaExport.execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.CREATE, metadata );
        assertEquals( 0, schemaExport.getExceptions().size() );

        // create w/o dropping again; should cause an exception because the tables exist already
		schemaExport.execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.CREATE, metadata );
        assertEquals( 1, schemaExport.getExceptions().size() );

        // drop tables only
		schemaExport.execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.DROP, metadata );
        assertEquals( 0, schemaExport.getExceptions().size() );
    }
