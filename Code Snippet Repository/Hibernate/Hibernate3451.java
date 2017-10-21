	public static String getElementContent(Element element, String defaultStr) throws Exception {
		if ( element == null ) {
			return defaultStr;
		}

		final NodeList children = element.getChildNodes();
		final StringBuilder result = new StringBuilder("");
		for ( int i = 0; i < children.getLength() ; i++ ) {
			if ( children.item( i ).getNodeType() == Node.TEXT_NODE
					|| children.item( i ).getNodeType() == Node.CDATA_SECTION_NODE ) {
				result.append( children.item( i ).getNodeValue() );
			}
//			else if ( children.item( i ).getNodeType() == Node.COMMENT_NODE ) {
//				// Ignore comment nodes
//			}
		}
		return result.toString().trim();
	}
