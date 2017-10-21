	@Override
	@SuppressWarnings("")
	protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		InputStream body = inputMessage.getBody();
		if (DOMSource.class == clazz) {
			return (T) readDOMSource(body);
		}
		else if (SAXSource.class == clazz) {
			return (T) readSAXSource(body);
		}
		else if (StAXSource.class == clazz) {
			return (T) readStAXSource(body);
		}
		else if (StreamSource.class == clazz || Source.class == clazz) {
			return (T) readStreamSource(body);
		}
		else {
			throw new HttpMessageConversionException("Could not read class [" + clazz +
					"]. Only DOMSource, SAXSource, StAXSource, and StreamSource are supported.");
		}
	}
