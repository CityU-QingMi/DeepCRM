		@Override
		@Nullable
		public Object getProperty(String name) throws SAXNotRecognizedException {
			if ("http://xml.org/sax/properties/lexical-handler".equals(name)) {
				return lexicalHandler;
			}
			else {
				throw new SAXNotRecognizedException(name);
			}
		}
