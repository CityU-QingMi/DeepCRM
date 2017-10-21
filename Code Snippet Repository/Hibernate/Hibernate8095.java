	@Override
	protected void prepareTest() throws Exception {
		super.prepareTest();
		SelectClause.VERSION2_SQL = true;
		DotNode.regressionStyleJoinSuppression = true;
		DotNode.ILLEGAL_COLL_DEREF_EXCP_BUILDER = new DotNode.IllegalCollectionDereferenceExceptionBuilder() {
			public QueryException buildIllegalCollectionDereferenceException(String propertyName, FromReferenceNode lhs) {
				throw new QueryException( "illegal syntax near collection: " + propertyName );
			}
		};
		SqlGenerator.REGRESSION_STYLE_CROSS_JOINS = true;
	}
