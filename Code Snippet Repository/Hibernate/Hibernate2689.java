	public void recursiveResolve(int level, boolean impliedAtRoot, String classAlias, AST parent)
			throws SemanticException {
		AST lhs = getFirstChild();
		int nextLevel = level + 1;
		if ( lhs != null ) {
			FromReferenceNode n = (FromReferenceNode) lhs;
			n.recursiveResolve( nextLevel, impliedAtRoot, null, this );
		}
		resolveFirstChild();
		boolean impliedJoin = true;
		if ( level == ROOT_LEVEL && !impliedAtRoot ) {
			impliedJoin = false;
		}
		resolve( true, impliedJoin, classAlias, parent );
	}
