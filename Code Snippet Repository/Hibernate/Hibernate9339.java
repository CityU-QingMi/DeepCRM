    @Test
    public void testGenerateDdlToFile() {
		final SchemaExport schemaExport = new SchemaExport();

        java.io.File outFile = new java.io.File("schema.ddl");
        schemaExport.setOutputFile( outFile.getPath() );

        // do not script to console or export to database
        schemaExport.execute( EnumSet.of( TargetType.SCRIPT ), SchemaExport.Action.DROP, metadata );
        if ( doesDialectSupportDropTableIfExist() && schemaExport.getExceptions().size() > 0 ) {
            assertEquals( 2, schemaExport.getExceptions().size() );
        }
        assertTrue( outFile.exists() );

        //check file is not empty
        assertTrue( outFile.length() > 0 );
        outFile.delete();
    }
