	@Before
	public void before() throws Exception {
		ssr = new StandardServiceRegistryBuilder()
				.build();
		binder = new XmlMappingBinderAccess( ssr ).getMappingBinder();

		final URL hbmXmlUrl = getClass().getClassLoader().getResource( HBM_RESOURCE_NAME );
		if ( hbmXmlUrl == null ) {
			throw couldNotFindHbmXmlFile();
		}
		hbmXmlFile = new File( hbmXmlUrl.getFile() );
		if ( ! hbmXmlFile.exists() ) {
			throw couldNotFindHbmXmlFile();
		}
		hbmXmlBinFile = CacheableFileXmlSource.determineCachedFile( hbmXmlFile );
	}
