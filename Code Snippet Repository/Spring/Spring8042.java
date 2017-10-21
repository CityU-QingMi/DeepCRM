	@Override
	public final void marshal(Object graph, Result result) throws IOException, XmlMappingException {
		if (result instanceof DOMResult) {
			marshalDomResult(graph, (DOMResult) result);
		}
		else if (StaxUtils.isStaxResult(result)) {
			marshalStaxResult(graph, result);
		}
		else if (result instanceof SAXResult) {
			marshalSaxResult(graph, (SAXResult) result);
		}
		else if (result instanceof StreamResult) {
			marshalStreamResult(graph, (StreamResult) result);
		}
		else {
			throw new IllegalArgumentException("Unknown Result type: " + result.getClass());
		}
	}
