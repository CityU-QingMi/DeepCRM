	protected Document parseXmlByteArray(byte[] xml, @Nullable String encoding) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(this.hasNamespaces);
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		InputSource inputSource = new InputSource(new ByteArrayInputStream(xml));
		if (StringUtils.hasText(encoding)) {
			inputSource.setEncoding(encoding);
		}
		return documentBuilder.parse(inputSource);
	}
