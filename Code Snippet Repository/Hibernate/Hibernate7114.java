	@Test
	public void testStrictCaseWhereFileDoesNotPreviouslyExist() throws FileNotFoundException {
		deleteBinFile();
		try {
			new MetadataSources( ssr ).addCacheableFileStrictly( hbmXmlFile ).buildMetadata();
			fail( "addCacheableFileStrictly should be led to MappingException when bin file does not exist" );
		}
		catch (MappingException ignore) {
			// this is the expected result
		}
	}
