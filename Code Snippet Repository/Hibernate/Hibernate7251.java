	@Before
	public void setUp() throws Exception {
		mappingFile = new File( getClass().getClassLoader().getResource( MAPPING ).toURI() );
		assertTrue( mappingFile.exists() );
		mappingBinFile = new File( mappingFile.getParentFile(), mappingFile.getName() + ".bin" );
		if ( mappingBinFile.exists() ) {
			//noinspection ResultOfMethodCallIgnored
			mappingBinFile.delete();
		}
	}
