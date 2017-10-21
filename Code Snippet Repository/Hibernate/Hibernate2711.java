	@Override
	public void resolve(
			boolean generateJoin,
			boolean implicitJoin,
			String classAlias,
			AST parent,
			AST parentPredicate) throws SemanticException {
		if (parent != null) {
			throw new SemanticException( expressionDescription() + " expression cannot be further de-referenced" );
		}
		super.resolve(generateJoin, implicitJoin, classAlias, parent, parentPredicate);
	}
