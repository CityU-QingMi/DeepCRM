	@Override
	protected void marshalDomNode(Object graph, Node node) throws XmlMappingException {
		try {
			// JiBX does not support DOM natively, so we write to a buffer first, and transform that to the Node
			Result result = new DOMResult(node);
			transformAndMarshal(graph, result);
		}
		catch (IOException ex) {
			throw new MarshallingFailureException("JiBX marshalling exception", ex);
		}
	}
