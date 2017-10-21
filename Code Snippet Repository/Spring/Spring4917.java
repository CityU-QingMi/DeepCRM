	@Override
	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		if (NAMESPACES_FEATURE_NAME.equals(name)) {
			return this.namespacesFeature;
		}
		else if (NAMESPACE_PREFIXES_FEATURE_NAME.equals(name)) {
			return this.namespacePrefixesFeature;
		}
		else if (IS_STANDALONE_FEATURE_NAME.equals(name)) {
			if (this.isStandalone != null) {
				return this.isStandalone;
			}
			else {
				throw new SAXNotSupportedException("startDocument() callback not completed yet");
			}
		}
		else {
			return super.getFeature(name);
		}
	}
