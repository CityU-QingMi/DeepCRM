	@Test
	public void testStringSplittingWithoutCatalogAndSchema() {
		QualifiedNameParser.NameParts nameParts = PARSER.parse(
				"MyEntity",
				null,
				null
		);

		assertThat( nameParts.getCatalogName(), is( nullValue() ) );
		assertThat( nameParts.getSchemaName(), is( nullValue() ) );
		assertThat( nameParts.getObjectName().getText(), is( "MyEntity" ) );
	}
