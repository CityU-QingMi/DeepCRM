	@Override
	protected AST quotedIdentifier(AST ident) {
/**/
/**/
/**/
		final String columnName = context.getDialect().quote( '`' + ident.getText() + '`' );
		columnReferences.add( columnName );
		final String marker = '{' + columnName + '}';
		return getASTFactory().create( OrderByTemplateTokenTypes.IDENT, marker );
	}
