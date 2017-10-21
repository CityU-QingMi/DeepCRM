	@Test
	public void testCachedFiles() throws Exception {
		assertFalse( mappingBinFile.exists() );
		// This call should create the cached file
		new Configuration().addCacheableFile( mappingFile );
		assertTrue( mappingBinFile.exists() );

		new Configuration().addCacheableFileStrictly( mappingFile );

		// make mappingBinFile obsolete by declaring it a minute older than mappingFile
		mappingBinFile.setLastModified( mappingFile.lastModified() - 60000L );

		new Configuration().addCacheableFile( mappingFile );
		assertTrue( mappingBinFile.exists() );
		assertTrue( "mappingFile should have been recreated.", mappingBinFile.lastModified() >= mappingFile.lastModified());
	}
