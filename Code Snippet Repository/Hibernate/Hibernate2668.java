	private FromElement evaluateFromElementPath(String path, String classAlias) throws SemanticException {
		ASTFactory factory = fromClause.getASTFactory();
		FromReferenceNode pathNode = (FromReferenceNode) PathHelper.parsePath( path, factory );
		pathNode.recursiveResolve(
				// This is the root level node.
				FromReferenceNode.ROOT_LEVEL,
				// Generate an explicit from clause at the root.
				false,
				classAlias,
				null
		);
		if ( pathNode.getImpliedJoin() != null ) {
			return pathNode.getImpliedJoin();
		}
		return pathNode.getFromElement();
	}
