	@SuppressWarnings("")
	private Source processSource(Source source) {
		if (StaxUtils.isStaxSource(source) || source instanceof DOMSource) {
			return source;
		}

		XMLReader xmlReader = null;
		InputSource inputSource = null;

		if (source instanceof SAXSource) {
			SAXSource saxSource = (SAXSource) source;
			xmlReader = saxSource.getXMLReader();
			inputSource = saxSource.getInputSource();
		}
		else if (source instanceof StreamSource) {
			StreamSource streamSource = (StreamSource) source;
			if (streamSource.getInputStream() != null) {
				inputSource = new InputSource(streamSource.getInputStream());
			}
			else if (streamSource.getReader() != null) {
				inputSource = new InputSource(streamSource.getReader());
			}
			else {
				inputSource = new InputSource(streamSource.getSystemId());
			}
		}

		try {
			if (xmlReader == null) {
				xmlReader = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			}
			xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", !isSupportDtd());
			String name = "http://xml.org/sax/features/external-general-entities";
			xmlReader.setFeature(name, isProcessExternalEntities());
			if (!isProcessExternalEntities()) {
				xmlReader.setEntityResolver(NO_OP_ENTITY_RESOLVER);
			}
			return new SAXSource(xmlReader, inputSource);
		}
		catch (SAXException ex) {
			logger.warn("Processing of external entities could not be disabled", ex);
			return source;
		}
	}
