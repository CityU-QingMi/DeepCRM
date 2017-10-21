	private DOMSource readDOMSource(InputStream body) throws IOException {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			documentBuilderFactory.setFeature(
					"http://apache.org/xml/features/disallow-doctype-decl", !isSupportDtd());
			documentBuilderFactory.setFeature(
					"http://xml.org/sax/features/external-general-entities", isProcessExternalEntities());
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			if (!isProcessExternalEntities()) {
				documentBuilder.setEntityResolver(NO_OP_ENTITY_RESOLVER);
			}
			Document document = documentBuilder.parse(body);
			return new DOMSource(document);
		}
		catch (NullPointerException ex) {
			if (!isSupportDtd()) {
				throw new HttpMessageNotReadableException("NPE while unmarshalling: " +
						"This can happen due to the presence of DTD declarations which are disabled.", ex);
			}
			throw ex;
		}
		catch (ParserConfigurationException ex) {
			throw new HttpMessageNotReadableException("Could not set feature: " + ex.getMessage(), ex);
		}
		catch (SAXException ex) {
			throw new HttpMessageNotReadableException("Could not parse document: " + ex.getMessage(), ex);
		}
	}
