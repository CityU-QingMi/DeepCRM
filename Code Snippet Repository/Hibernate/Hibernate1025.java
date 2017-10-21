	public Binding bind(InputStreamAccess xmlInputStreamAccess) {
		LOG.tracef( "reading mappings from InputStreamAccess : %s", xmlInputStreamAccess.getStreamName() );

		final Origin origin = new Origin( SourceType.INPUT_STREAM, xmlInputStreamAccess.getStreamName() );
		InputStream xmlInputStream = xmlInputStreamAccess.accessInputStream();
		try {
			return new InputStreamXmlSource( origin, xmlInputStream, false ).doBind( mappingBinder );
		}
		finally {
			try {
				xmlInputStream.close();
			}
			catch (IOException e) {
				LOG.debugf( "Unable to close InputStream obtained from InputStreamAccess : " + xmlInputStreamAccess.getStreamName() );
			}
		}
	}
