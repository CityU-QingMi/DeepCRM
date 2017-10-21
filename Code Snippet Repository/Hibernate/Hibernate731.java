	private Binding doBind(XMLEventReader eventReader, Origin origin) {
		try {
			final StartElement rootElementStartEvent = seekRootElementStartEvent( eventReader, origin );
			return doBind( eventReader, rootElementStartEvent, origin );
		}
		finally {
			try {
				eventReader.close();
			}
			catch ( Exception e ) {
				log.debug( "Unable to close StAX reader", e );

			}
		}
	}
