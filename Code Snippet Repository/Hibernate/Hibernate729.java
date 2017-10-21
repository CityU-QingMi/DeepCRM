	protected XMLEventReader createReader(InputStream stream, Origin origin) {
		try {
			// create a standard StAX reader
			final XMLEventReader staxReader = staxFactory().createXMLEventReader( stream );
			// and wrap it in a buffered reader (keeping 100 element sized buffer)
			return new BufferedXMLEventReader( staxReader, 100 );
		}
		catch ( XMLStreamException e ) {
			throw new MappingException( "Unable to create stax reader", e, origin );
		}
	}
