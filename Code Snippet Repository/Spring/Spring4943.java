	private void handleStartDocument(final XMLEvent event) throws SAXException {
		if (event.isStartDocument()) {
			StartDocument startDocument = (StartDocument) event;
			String xmlVersion = startDocument.getVersion();
			if (StringUtils.hasLength(xmlVersion)) {
				this.xmlVersion = xmlVersion;
			}
			if (startDocument.encodingSet()) {
				this.encoding = startDocument.getCharacterEncodingScheme();
			}
		}
		if (getContentHandler() != null) {
			final Location location = event.getLocation();
			getContentHandler().setDocumentLocator(new Locator2() {
				@Override
				public int getColumnNumber() {
					return (location != null ? location.getColumnNumber() : -1);
				}
				@Override
				public int getLineNumber() {
					return (location != null ? location.getLineNumber() : -1);
				}
				@Override
				@Nullable
				public String getPublicId() {
					return (location != null ? location.getPublicId() : null);
				}
				@Override
				@Nullable
				public String getSystemId() {
					return (location != null ? location.getSystemId() : null);
				}
				@Override
				public String getXMLVersion() {
					return xmlVersion;
				}
				@Override
				@Nullable
				public String getEncoding() {
					return encoding;
				}
			});
			getContentHandler().startDocument();
		}
	}
