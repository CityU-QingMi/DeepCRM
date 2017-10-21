	@Override
	public void resolveInFunctionCall(boolean generateJoin, boolean implicitJoin) throws SemanticException {
		if ( isResolved() ) {
			return;
		}
		Type propertyType = prepareLhs();            // Prepare the left hand side and get the data type.
		if ( propertyType != null && propertyType.isCollectionType() ) {
			resolveIndex( null );
		}
		else {
			resolveFirstChild();
			super.resolve( generateJoin, implicitJoin );
		}
	}
