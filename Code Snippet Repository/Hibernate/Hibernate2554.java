	private AST generateSyntheticDotNodeForNonQualifiedPropertyRef(AST property, FromElement fromElement) {
		AST dot = getASTFactory().create( DOT, "{non-qualified-property-ref}" );
		// TODO : better way?!?
		( (DotNode) dot ).setPropertyPath( ( (FromReferenceNode) property ).getPath() );

		IdentNode syntheticAlias = (IdentNode) getASTFactory().create( IDENT, "{synthetic-alias}" );
		syntheticAlias.setFromElement( fromElement );
		syntheticAlias.setResolved();

		dot.setFirstChild( syntheticAlias );
		dot.addChild( property );

		return dot;
	}
