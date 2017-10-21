	public void processConstant(AST constant, boolean resolveIdent) throws SemanticException {
		// If the constant is an IDENT, figure out what it means...
		boolean isIdent = ( constant.getType() == IDENT || constant.getType() == WEIRD_IDENT );
		if ( resolveIdent && isIdent && isAlias( constant.getText() ) ) {
			// IDENT is a class alias in the FROM.
			IdentNode ident = (IdentNode) constant;
			// Resolve to an identity column.
			ident.resolve( false, true );
		}
		else {
			// IDENT might be the name of a class.
			Queryable queryable = walker.getSessionFactoryHelper().findQueryableUsingImports( constant.getText() );
			if ( isIdent && queryable != null ) {
				constant.setText( queryable.getDiscriminatorSQLValue() );
			}
			// Otherwise, it's a literal.
			else {
				processLiteral( constant );
			}
		}
	}
