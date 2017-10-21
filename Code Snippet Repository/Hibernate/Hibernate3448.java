	public static Iterator getChildrenByTagName(
			Element element,
			String tagName) {
		if ( element == null ) {
			return null;
		}
		// getElementsByTagName gives the corresponding elements in the whole
		// descendance. We want only children

		NodeList children = element.getChildNodes();
		ArrayList goodChildren = new ArrayList();
		for ( int i = 0; i < children.getLength() ; i++ ) {
			Node currentChild = children.item( i );
			if ( currentChild.getNodeType() == Node.ELEMENT_NODE &&
					( (Element) currentChild ).getTagName().equals( tagName ) ) {
				goodChildren.add( currentChild );
			}
		}
		return goodChildren.iterator();
	}
