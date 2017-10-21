	private String toPathText(AST node) {
		final String text = node.getText();
		if ( text.equals( "." )
				&& node.getFirstChild() != null
				&& node.getFirstChild().getNextSibling() != null
				&& node.getFirstChild().getNextSibling().getNextSibling() == null ) {
			return toPathText( node.getFirstChild() ) + '.' + toPathText( node.getFirstChild().getNextSibling() );
		}
		return text;
	}
