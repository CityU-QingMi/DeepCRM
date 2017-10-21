	public String nodeToString(AST ast, boolean showClassName) {
		if ( ast == null ) {
			return "{node:null}";
		}
		StringBuilder buf = new StringBuilder();
		buf.append( "[" ).append( getTokenTypeName( ast.getType() ) ).append( "] " );
		if ( showClassName ) {
			buf.append( StringHelper.unqualify( ast.getClass().getName() ) ).append( ": " );
		}

		buf.append( "'" );
		String text = ast.getText();
		if ( text == null ) {
			text = "{text:null}";
		}
		appendEscapedMultibyteChars( text, buf );
		buf.append( "'" );
		if ( ast instanceof DisplayableNode ) {
			DisplayableNode displayableNode = (DisplayableNode) ast;
			// Add a space before the display text.
			buf.append( " " ).append( displayableNode.getDisplayText() );
		}
		return buf.toString();
	}
