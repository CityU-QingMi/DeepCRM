	private String resolveFunctionArgument(AST argumentNode) {
		final String nodeText = argumentNode.getText();
		final String adjustedText;
		if ( nodeText.contains( Template.TEMPLATE ) ) {
			// we have a SQL order-by fragment
			adjustedText = adjustTemplateReferences( nodeText );
		}
		else if ( nodeText.startsWith( "{" ) && nodeText.endsWith( "}" ) ) {
			columnReferences.add( nodeText.substring( 1, nodeText.length() - 1 ) );
			return nodeText;
		}
		else {
			adjustedText = nodeText;
			// because we did not process the node text, we need to attempt to find any column references
			// contained in it.
			// NOTE : uses regex for the time being; we should check the performance of this
			Pattern pattern = Pattern.compile( "\\{(.*)\\}" );
			Matcher matcher = pattern.matcher( adjustedText );
			while ( matcher.find() ) {
				columnReferences.add( matcher.group( 1 ) );
			}
		}
		return adjustedText;
	}
