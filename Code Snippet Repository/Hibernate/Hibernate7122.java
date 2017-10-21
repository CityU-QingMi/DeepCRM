	@Test
	public void testStringSplittingWithCatalogAndSchema() {
		QualifiedNameParser.NameParts nameParts = PARSER.parse(
				"schema.catalog.MyEntity",
				DEFAULT_CATALOG,
				DEFAULT_SCHEMA
		);

		assertThat( nameParts.getCatalogName().getText(), is( DEFAULT_CATALOG.getText() ) );
		assertThat( nameParts.getSchemaName().getText(), is( DEFAULT_SCHEMA.getText() ) );
		assertThat( nameParts.getObjectName().getText(), is( "MyEntity" ) );
	}
