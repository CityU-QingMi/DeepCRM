	@SuppressWarnings("")
	protected Source processSource(Source source) {
		if (source instanceof StreamSource) {
			StreamSource streamSource = (StreamSource) source;
			InputSource inputSource = new InputSource(streamSource.getInputStream());
			try {
				XMLReader xmlReader = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
				xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", !isSupportDtd());
				String featureName = "http://xml.org/sax/features/external-general-entities";
				xmlReader.setFeature(featureName, isProcessExternalEntities());
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
		else {
			return source;
		}
	}
