	protected Document buildDocument() {
		try {
			DocumentBuilder documentBuilder;
			synchronized (this.documentBuilderFactoryMonitor) {
				if (this.documentBuilderFactory == null) {
					this.documentBuilderFactory = createDocumentBuilderFactory();
				}
				documentBuilder = createDocumentBuilder(this.documentBuilderFactory);
			}
			return documentBuilder.newDocument();
		}
		catch (ParserConfigurationException ex) {
			throw new UnmarshallingFailureException("Could not create document placeholder: " + ex.getMessage(), ex);
		}
	}
