	@Override
	protected AST resolveIdent(AST ident) {
/**/
/**/
/**/
/**/
		String text = ident.getText();
		SqlValueReference[] sqlValueReferences;
		try {
			sqlValueReferences = context.getColumnMapper().map( text );
		}
		catch (Throwable t) {
			sqlValueReferences = null;
		}

		if ( sqlValueReferences == null || sqlValueReferences.length == 0 ) {
			return getASTFactory().create( OrderByTemplateTokenTypes.IDENT, makeColumnReference( text ) );
		}
		else if ( sqlValueReferences.length == 1 ) {
			return processSqlValueReference( sqlValueReferences[0] );
		}
		else {
			final AST root = getASTFactory().create( OrderByTemplateTokenTypes.IDENT_LIST, "{ident list}" );
			for ( SqlValueReference sqlValueReference : sqlValueReferences ) {
				root.addChild( processSqlValueReference( sqlValueReference ) );
			}
			return root;
		}
	}
