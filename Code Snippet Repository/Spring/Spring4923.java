	@Override
	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
		if (name.startsWith("http://xml.org/sax/features/")) {
			if (value) {
				throw new SAXNotSupportedException(name);
			}
		}
		else {
			throw new SAXNotRecognizedException(name);
		}
	}
