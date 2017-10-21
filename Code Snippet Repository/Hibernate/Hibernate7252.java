	@After
	public void tearDown() throws Exception {
		if ( mappingBinFile != null && mappingBinFile.exists() ) {
			// be nice
			//noinspection ResultOfMethodCallIgnored
			mappingBinFile.delete();
		}
		mappingBinFile = null;
		mappingFile = null;
	}
