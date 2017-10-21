	@Override
	public final Object unmarshal(Source source) throws IOException, XmlMappingException {
		if (source instanceof DOMSource) {
			return unmarshalDomSource((DOMSource) source);
		}
		else if (StaxUtils.isStaxSource(source)) {
			return unmarshalStaxSource(source);
		}
		else if (source instanceof SAXSource) {
			return unmarshalSaxSource((SAXSource) source);
		}
		else if (source instanceof StreamSource) {
			return unmarshalStreamSource((StreamSource) source);
		}
		else {
			throw new IllegalArgumentException("Unknown Source type: " + source.getClass());
		}
	}
