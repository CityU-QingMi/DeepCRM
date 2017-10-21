	private void prepareAnyImplicitJoins(DotNode dotNode) throws SemanticException {
		if ( dotNode.getLhs() instanceof DotNode ) {
			DotNode lhs = (DotNode) dotNode.getLhs();
			FromElement lhsOrigin = lhs.getFromElement();
			if ( lhsOrigin != null && "".equals( lhsOrigin.getText() ) ) {
				String lhsOriginText = lhsOrigin.getQueryable().getTableName() +
						" " + lhsOrigin.getTableAlias();
				lhsOrigin.setText( lhsOriginText );
			}
			prepareAnyImplicitJoins( lhs );
		}
	}
