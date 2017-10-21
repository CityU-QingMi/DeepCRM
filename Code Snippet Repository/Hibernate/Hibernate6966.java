	@Test
	public void testAll() throws Exception {
		final XMLHelper xmlHelper = new XMLHelper( ClassLoaderServiceTestingImpl.INSTANCE );
		final XMLContext context = new XMLContext( ClassLoaderAccessTestingImpl.INSTANCE );

		InputStream is = ClassLoaderServiceTestingImpl.INSTANCE.locateResourceStream(
				"org/hibernate/test/annotations/reflection/orm.xml"
		);
		Assert.assertNotNull( "ORM.xml not found", is );

		final ErrorLogger errorLogger = new ErrorLogger();
		final SAXReader saxReader = xmlHelper.createSAXReader( errorLogger, EJB3DTDEntityResolver.INSTANCE );

		try {
			saxReader.setFeature( "http://apache.org/xml/features/validation/schema", true );
		}
		catch ( SAXNotSupportedException e ) {
			saxReader.setValidation( false );
		}
		org.dom4j.Document doc;
		try {
			doc = saxReader.read( new InputSource( new BufferedInputStream( is ) ) );
		}
		finally {
			try {
				is.close();
			}
			catch ( IOException ioe ) {
				//log.warn( "Could not close input stream", ioe );
			}
		}
		Assert.assertFalse( errorLogger.hasErrors() );
		context.addDocument( doc );
	}
