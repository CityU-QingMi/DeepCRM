	@Test
	public void testAddingMultipleExtraPhysicalTableTypes() throws Exception {
		buildMetadata( "BASE, BASE TABLE" );
		InformationExtractorJdbcDatabaseMetaDataImplTest informationExtractor = buildInformationExtractorJdbcDatabaseMetaDataImplTest();

		assertThat( informationExtractor.isPhysicalTableType( "BASE TABLE" ), is( true ) );
		assertThat( informationExtractor.isPhysicalTableType( "BASE" ), is( true ) );
		assertThat( informationExtractor.isPhysicalTableType( "TABLE" ), is( true ) );
		assertThat( informationExtractor.isPhysicalTableType( "TABLE 1" ), is( false ) );
	}
