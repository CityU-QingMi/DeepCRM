	private XMLContext buildContext(String ormfile) throws SAXException, DocumentException, IOException {
		XMLHelper xmlHelper = new XMLHelper( ClassLoaderServiceTestingImpl.INSTANCE );
		InputStream is = ClassLoaderServiceTestingImpl.INSTANCE.locateResourceStream( ormfile );
		assertNotNull( "ORM.xml not found: " + ormfile, is );
		XMLContext context = new XMLContext( ClassLoaderAccessTestingImpl.INSTANCE );
		ErrorLogger errorLogger = new ErrorLogger();
		SAXReader saxReader = xmlHelper.createSAXReader( errorLogger, EJB3DTDEntityResolver.INSTANCE );
		//saxReader.setValidation( false );
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
			is.close();
		}
		if ( errorLogger.hasErrors() ) {
			System.out.println( errorLogger.getErrors().get( 0 ) );
		}
		assertFalse( errorLogger.hasErrors() );
		context.addDocument( doc );
		return context;
	}
