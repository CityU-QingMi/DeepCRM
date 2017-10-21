	@Test
	public void testStrictCaseWhereFileDoesPreviouslyExist() throws FileNotFoundException {
		deleteBinFile();
		createBinFile();
		try {
			new MetadataSources( ssr ).addCacheableFileStrictly( hbmXmlFile ).buildMetadata();
		}
		catch (MappingException e) {
			fail( "addCacheableFileStrictly led to MappingException when bin file existed" );
		}
	}
