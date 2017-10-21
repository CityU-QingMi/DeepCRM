	public void start(QueryTranslatorImpl q) {
		entityName = null;
		collectionName = null;
		alias = null;
		afterIn = false;
		afterAs = false;
		afterClass = false;
		expectingJoin = false;
		expectingIn = false;
		expectingAs = false;
		memberDeclarations = false;
		expectingPathExpression = false;
		afterMemberDeclarations = false;
		joinType = JoinType.NONE;
	}
