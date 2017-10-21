	private AST createFromElement(String text) {
		AST ast = ASTUtil.create(
				fromClause.getASTFactory(),
				implied ? IMPLIED_FROM : FROM_FRAGMENT, // This causes the factory to instantiate the desired class.
				text
		);
		// Reset the node type, because the rest of the system is expecting FROM_FRAGMENT, all we wanted was
		// for the factory to create the right sub-class.  This might get reset again later on anyway to make the
		// SQL generation simpler.
		ast.setType( FROM_FRAGMENT );
		return ast;
	}
