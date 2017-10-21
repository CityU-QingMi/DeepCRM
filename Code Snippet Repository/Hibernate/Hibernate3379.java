	private static String extractContent(Element element, String defaultStr) {
		if ( element == null ) {
			return defaultStr;
		}

		NodeList children = element.getChildNodes();
		StringBuilder result = new StringBuilder("");
		for ( int i = 0; i < children.getLength() ; i++ ) {
			if ( children.item( i ).getNodeType() == Node.TEXT_NODE ||
					children.item( i ).getNodeType() == Node.CDATA_SECTION_NODE ) {
				result.append( children.item( i ).getNodeValue() );
			}
		}
		return result.toString().trim();
	}
