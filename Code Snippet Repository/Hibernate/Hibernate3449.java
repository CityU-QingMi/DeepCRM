	public static Element getUniqueChild(Element element, String tagName) throws Exception {
		final Iterator goodChildren = getChildrenByTagName( element, tagName );

		if ( goodChildren != null && goodChildren.hasNext() ) {
			final Element child = (Element) goodChildren.next();
			if ( goodChildren.hasNext() ) {
				throw new Exception( "expected only one " + tagName + " tag" );
			}
			return child;
		}
		else {
			throw new Exception( "expected one " + tagName + " tag" );
		}
	}
