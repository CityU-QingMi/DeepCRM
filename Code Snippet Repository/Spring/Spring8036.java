	@Override
	protected void marshalSaxHandlers(Object graph, ContentHandler contentHandler, @Nullable LexicalHandler lexicalHandler)
			throws XmlMappingException {
		try {
			// JiBX does not support SAX natively, so we write to a buffer first, and transform that to the handlers
			SAXResult saxResult = new SAXResult(contentHandler);
			saxResult.setLexicalHandler(lexicalHandler);
			transformAndMarshal(graph, saxResult);
		}
		catch (IOException ex) {
			throw new MarshallingFailureException("JiBX marshalling exception", ex);
		}
	}
