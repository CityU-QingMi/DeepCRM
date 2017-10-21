	@Override
	public Binding bind(InputStream stream, Origin origin) {
		final XMLEventReader eventReader = createReader( stream, origin );
		try {
			return doBind( eventReader, origin );
		}
		finally {
			try {
				eventReader.close();
			}
			catch (XMLStreamException e) {
				log.debug( "Unable to close StAX reader", e );
			}
		}
	}
