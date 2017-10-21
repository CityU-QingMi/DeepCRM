	private void handleStartDocument() throws SAXException {
		if (XMLStreamConstants.START_DOCUMENT == this.reader.getEventType()) {
			String xmlVersion = this.reader.getVersion();
			if (StringUtils.hasLength(xmlVersion)) {
				this.xmlVersion = xmlVersion;
			}
			this.encoding = this.reader.getCharacterEncodingScheme();
		}
		if (getContentHandler() != null) {
			final Location location = this.reader.getLocation();
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
			if (this.reader.standaloneSet()) {
				setStandalone(this.reader.isStandalone());
			}
		}
	}
