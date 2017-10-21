	@Test
	public void testSchemaExport() throws Exception {
		SchemaExport schemaExport = new SchemaExport();
		schemaExport.create( EnumSet.of( TargetType.STDOUT, TargetType.DATABASE ), metadata );

		List<SQLException> exceptions = schemaExport.getExceptions();
		for ( SQLException exception : exceptions ) {
			assertThat( exception.getMessage(), exception.getSQLState(), not( "42000" ) );
		}
	}
