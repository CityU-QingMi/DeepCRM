	public List<TestDataElement> read(String fileName) {
		if ( fileName == null ) {
			throw new RuntimeException( "Null testsuite-suite data file specified." );
		}
		List<TestDataElement> testDataElements = new ArrayList<TestDataElement>();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read( getInputStream( fileName ) );
			addDataElements( document, testDataElements );
		}
		catch (DocumentException e) {
			throw new RuntimeException( e );
		}
		return testDataElements;
	}
